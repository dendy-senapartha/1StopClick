package com.vlcplayer.activities

import android.content.*
import android.net.Uri
import android.os.Bundle
import android.os.IBinder
import android.support.v4.media.session.MediaControllerCompat


import android.support.v4.media.session.PlaybackStateCompat
import androidx.fragment.app.Fragment
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.vlcplayer.R

import com.vlcplayer.dagger.injectors.InjectableAppCompatActivity
import com.vlcplayer.fragments.CastPlayerFragment
import com.vlcplayer.fragments.LocalPlayerFragment
import com.vlcplayer.services.MediaPlayerService
import com.vlcplayer.services.binders.MediaPlayerServiceBinder


class MediaPlayerActivity : InjectableAppCompatActivity() {

    companion object {
        @JvmStatic
        val MediaUri = "extra.mediauri"
        @JvmStatic
        val SubtitleUri = "extra.subtitleuri"
        @JvmStatic
        val SubtitleDestinationUri = "extra.subtitledestinationuri"
        @JvmStatic
        val SubtitleLanguageCode = "extra.subtitlelanguagecode"
        @JvmStatic
        val OpenSubtitlesUserAgent = "extra.useragent"
    }

    private var mediaController: MediaControllerCompat? = null
    private var mediaPlayerServiceBinder: MediaPlayerServiceBinder? = null
    private var localPlayerFragment: LocalPlayerFragment? = null
    private var castPlayerFragment: CastPlayerFragment? = null

    private val broadCastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val action = intent.action ?: return

            when (action) {
                MediaPlayerService.RendererClearedAction -> showLocalPlayerFragment()
                MediaPlayerService.RendererSelectionAction -> showCastPlayerFragment()
            }
        }
    }

    private val mediaPlayerServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(componentName: ComponentName, iBinder: IBinder) {
            mediaPlayerServiceBinder = iBinder as MediaPlayerServiceBinder

            registerMediaController(iBinder)

            if (mediaPlayerServiceBinder?.selectedRendererItem == null) {
                showLocalPlayerFragment()
            } else {
                showCastPlayerFragment()
            }
        }

        override fun onServiceDisconnected(componentName: ComponentName) {
            mediaPlayerServiceBinder = null

            mediaController?.unregisterCallback(controllerCallback)
        }
    }

    private fun registerMediaController(serviceBinder: MediaPlayerServiceBinder?) {
        if (serviceBinder == null) {
            return
        }

        mediaController = MediaControllerCompat(
                this,
                serviceBinder.mediaSession!!
        ).apply {
            registerCallback(controllerCallback)
        }

        MediaControllerCompat.setMediaController(this, mediaController)
    }

    private val controllerCallback = object : MediaControllerCompat.Callback() {
        override fun onPlaybackStateChanged(state: PlaybackStateCompat) {
            localPlayerFragment?.configure(state)
            castPlayerFragment?.configure(state)
        }
    }

    private fun getLocalPlayerFragment(): LocalPlayerFragment = supportFragmentManager
            .findFragmentByTag(LocalPlayerFragment.Tag) as? LocalPlayerFragment
            ?: LocalPlayerFragment.createInstance(
                    //mediaUri = intent.getParcelableExtra(MediaUri)
                    mediaUri = Uri.parse("https://archive.org/download/ksnn_compilation_master_the_internet/ksnn_compilation_master_the_internet_512kb.mp4")
                    , subtitleUri = intent.getParcelableExtra(SubtitleUri)
                    , subtitleDestinationUri = intent.getParcelableExtra(SubtitleDestinationUri)
                    , openSubtitlesUserAgent = intent.getStringExtra(OpenSubtitlesUserAgent)
                    , subtitleLanguageCode = intent.getStringExtra(SubtitleLanguageCode)
            )

    private fun getCastPlayerFragment(): CastPlayerFragment = supportFragmentManager
            .findFragmentByTag(CastPlayerFragment.Tag) as? CastPlayerFragment
            ?: CastPlayerFragment.createInstance(
                    mediaUri = intent.getParcelableExtra(MediaUri)
                    , subtitleUri = intent.getParcelableExtra(SubtitleUri)
                    , subtitleDestinationUri = intent.getParcelableExtra(SubtitleDestinationUri)
                    , openSubtitlesUserAgent = intent.getStringExtra(OpenSubtitlesUserAgent)
                    , subtitleLanguageCode = intent.getStringExtra(SubtitleLanguageCode)
            )

    private fun showFragment(
            fragment: Fragment
            , tag: String
    ) = supportFragmentManager
            .beginTransaction()
            .replace(R.id.framelayout_fragment_container, fragment, tag)
            .commit()

    private fun showLocalPlayerFragment() {
        castPlayerFragment = null
        localPlayerFragment = getLocalPlayerFragment()

        showFragment(localPlayerFragment!!, LocalPlayerFragment.Tag)
    }

    private fun showCastPlayerFragment() {
        localPlayerFragment = null
        castPlayerFragment = getCastPlayerFragment()

        showFragment(castPlayerFragment!!, CastPlayerFragment.Tag)
    }

    private fun registerRendererBroadcastReceiver() = LocalBroadcastManager
            .getInstance(this)
            .registerReceiver(broadCastReceiver, IntentFilter().apply {
                addAction(MediaPlayerService.RendererClearedAction)
                addAction(MediaPlayerService.RendererSelectionAction)
            })

    private fun bindMediaPlayerService() = bindService(
            Intent(applicationContext, MediaPlayerService::class.java)
            , mediaPlayerServiceConnection
            , Context.BIND_AUTO_CREATE
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_media_player)
    }

    override fun onStart() {
        super.onStart()

        bindMediaPlayerService()
        registerRendererBroadcastReceiver()

        startService(Intent(applicationContext, MediaPlayerService::class.java))
    }

    override fun onStop() {
        unbindService(mediaPlayerServiceConnection)

        mediaController?.unregisterCallback(controllerCallback)

        LocalBroadcastManager
                .getInstance(this)
                .unregisterReceiver(broadCastReceiver)

        castPlayerFragment = null

        super.onStop()
    }

    override fun onBackPressed() {
        // Always ensure that we stop the media player service when navigating back.
        stopService(Intent(applicationContext, MediaPlayerService::class.java))

        super.onBackPressed()
    }


}