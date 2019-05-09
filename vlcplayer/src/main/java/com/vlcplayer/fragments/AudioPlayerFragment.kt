package com.vlcplayer.fragments

import android.annotation.TargetApi
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.media.AudioManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.support.v4.media.session.MediaControllerCompat
import android.support.v4.media.session.PlaybackStateCompat
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ProgressBar
import com.bumptech.glide.Glide
import com.vlcplayer.R
import com.vlcplayer.common.AndroidJob
import com.vlcplayer.common.extensions.setColor
import com.vlcplayer.common.utils.*
import com.vlcplayer.components.AudioPlayerControlComponent

import com.vlcplayer.contracts.MediaPlayer
import kotlinx.android.synthetic.main.fragment_audio_player.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.videolan.libvlc.IVLCVout

/*
* Created by dendy-prtha on 24/04/2019.
* TODO: Add a class header comment!
*/

internal class AudioPlayerFragment : MediaPlayerServiceFragment()
        , AudioPlayerControlComponent.Callback
        , MediaPlayer.Callback
        , IVLCVout.OnNewVideoLayoutListener {

    private val mediaTitle: String get() = arguments!!.getString(AudioPlayerFragment.MediaTitleKey)
    private val mediaUri: Uri get() = arguments!!.getParcelable(AudioPlayerFragment.MediaUriKey)
    private val audioImageUri: Uri get() = arguments!!.getParcelable(AudioPlayerFragment.MediaImageUriKey)

    private lateinit var progressBar: ProgressBar

    private var resumeIsPlaying = true
    private var resumeLength: Long = 0
    private var resumeTime: Long = 0

    private val rootJob: AndroidJob = AndroidJob(lifecycle)

    companion object {
        const val Tag = "tag.AudioPlayerFragment"
        private const val MediaTitleKey = "bundle.mediatitle"
        private const val MediaUriKey = "bundle.mediauri"
        private const val MediaImageUriKey = "bundle.mediaimage"

        const val IsPlayingKey = "bundle.isplaying"
        const val LengthKey = "bundle.length"
        const val TimeKey = "bundle.time"

        @JvmStatic
        fun createInstance(
                mediaTitle: String
                , mediaUri: Uri
                , mediaImageUri: Uri
        ): AudioPlayerFragment = AudioPlayerFragment().apply {
            arguments = Bundle().apply {
                putString(MediaTitleKey, mediaTitle)
                putParcelable(MediaUriKey, mediaUri)
                putParcelable(MediaImageUriKey, mediaImageUri)
            }
        }

    }

    private val becomingNoisyReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            if (AudioManager.ACTION_AUDIO_BECOMING_NOISY == intent.action) {
                // Pause playback whenever the user pulls out ( ͡° ͜ʖ ͡°)
                serviceBinder?.pause()
            }
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? = inflater.inflate(
            R.layout.fragment_audio_player,
            container,
            false
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initProgressBar()
        subscribeToViewComponents()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState.apply {
            putBoolean(IsPlayingKey, resumeIsPlaying)
            putLong(TimeKey, resumeTime)
            putLong(LengthKey, resumeLength)
        })
    }

    override fun onResume() {
        super.onResume()

        context?.registerReceiver(
                becomingNoisyReceiver,
                IntentFilter(AudioManager.ACTION_AUDIO_BECOMING_NOISY)
        )
    }

    override fun onPause() {
        stopPlayback()

        serviceBinder?.callback = null

        context?.unregisterReceiver(becomingNoisyReceiver)

        super.onPause()
    }

    private fun subscribeToViewComponents() {
        componentAudioPlayerControl.registerCallback(this)
    }

    private fun initProgressBar() {
        val context = requireContext()

        progressBar = ProgressBar(
                context
                , null
                , android.R.attr.progressBarStyleLarge
        ).apply {
            visibility = View.GONE
            setColor(R.color.progress_bar_spinner)
        }

        val params = FrameLayout.LayoutParams(
                ResourceUtil.getDimenDp(context, R.dimen.player_spinner_width),
                ResourceUtil.getDimenDp(context, R.dimen.player_spinner_height)
        ).apply {
            gravity = Gravity.CENTER
        }

        (view as ViewGroup).addView(
                progressBar
                , params
        )
        Glide.with(this).load(audioImageUri.toString()).fitCenter().placeholder(R.drawable.defaut).into(trackImage)
    }

    private fun startPlayback() {
        serviceBinder?.setMedia(
                requireContext()
                , mediaUri
        )

        if (resumeIsPlaying) {
            serviceBinder?.play()
        }
    }

    private fun configure(
            isPlaying: Boolean,
            time: Long,
            length: Long
    ) = componentAudioPlayerControl.configure(
            isPlaying,
            time,
            length
    )

    fun configure(state: PlaybackStateCompat) = configure(
            state.state == PlaybackStateCompat.STATE_PLAYING,
            state.position,
            state.bufferedPosition
    )

    private fun stopPlayback() {
        updateResumeState()
        serviceBinder?.stop()
    }

    private fun updateResumeState() {
        val activity = activity ?: return

        val playbackState = MediaControllerCompat
                .getMediaController(activity)
                .playbackState

        resumeIsPlaying = playbackState.state == PlaybackStateCompat.STATE_PLAYING
        resumeTime = playbackState.position
        resumeLength = playbackState.bufferedPosition
    }

    override fun onServiceConnected() {
        serviceBinder?.callback = this

        startPlayback()
    }

    override fun onPlayPauseButtonClicked() {
        serviceBinder?.togglePlayback()
    }

    override fun onProgressChanged(progress: Int) {
        serviceBinder?.setProgress(progress)
        serviceBinder?.play()
    }

    override fun onProgressChangeStarted() {
        serviceBinder?.pause()
    }

    override fun onPlayerOpening() {
        // Intentionally left blank..
    }

    override fun onPlayerSeekStateChange(canSeek: Boolean) {
        if (!canSeek) {
            return
        }

        serviceBinder?.setTime(resumeTime)
    }

    override fun onPlayerPlaying() {
        // Intentionally left blank..
    }

    override fun onPlayerPaused() {
        // Intentionally left blank..
    }

    override fun onPlayerStopped() {
        // Intentionally left blank..
    }

    override fun onPlayerEndReached() {
        activity?.finish()
    }

    override fun onPlayerError() {
        // Intentionally left blank..
    }

    override fun onPlayerTimeChange(timeChanged: Long) {
        // Intentionally left blank..
    }

    override fun onBuffering(buffering: Float) {
        if (buffering == 100f) {
            GlobalScope.launch(Dispatchers.Main) { progressBar.visibility = View.GONE }
            return
        }

        if (progressBar.visibility == View.VISIBLE) {
            return
        }

        GlobalScope.launch(Dispatchers.Main)  { progressBar.visibility = View.VISIBLE }
    }

    override fun onPlayerPositionChanged(positionChanged: Float) {
        // Intentionally left blank..
    }

    override fun onSubtitlesCleared() {
        startPlayback()
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun onNewVideoLayout(p0: IVLCVout?, p1: Int, p2: Int, p3: Int, p4: Int, p5: Int, p6: Int) {

        updateAudioImageArt()
    }

    private fun updateAudioImageArt() {
        //todo : need to update image size
    }

}