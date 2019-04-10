package com.vlcplayer.dagger.modules

import com.vlcplayer.activities.MediaPlayerActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
@Suppress("unused")
abstract class ActivityModule {

    @ContributesAndroidInjector()
    abstract fun contributeMediaPlayerActivity(): MediaPlayerActivity

}
