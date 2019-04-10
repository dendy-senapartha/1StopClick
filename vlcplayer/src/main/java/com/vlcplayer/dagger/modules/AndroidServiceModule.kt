package com.vlcplayer.dagger.modules

import com.vlcplayer.services.MediaPlayerService
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
@Suppress("unused")
abstract class AndroidServiceModule {

    @ContributesAndroidInjector
    abstract fun contributesMediaPlayerService(): MediaPlayerService

}
