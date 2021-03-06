package com.vlcplayer.dagger.modules


import android.content.Context
import com.vlcplayer.VlcMediaPlayer
import com.vlcplayer.VlcOptionsProvider

import dagger.Module
import dagger.Provides
import org.videolan.libvlc.LibVLC


/**
 * This module is responsible for providing VLC dependencies.
 */
@Module
@Suppress("unused")
class VlcModule {

    @Provides
    internal fun provideLibVlc(context: Context): LibVLC {
        val appContext = context.applicationContext

        val options = VlcOptionsProvider
                .getInstance()
                .options

        return if (options == null || options.size == 0)
        // No options provided, build defaults.
            LibVLC(appContext, VlcOptionsProvider.Builder(context).build())
        else
        // Use provided options.
            LibVLC(appContext, options)
    }

    @Provides
    internal fun provideVlcMediaPlayer(libVlc: LibVLC): com.vlcplayer.contracts.VlcMediaPlayer {
        return VlcMediaPlayer(libVlc)
    }


}