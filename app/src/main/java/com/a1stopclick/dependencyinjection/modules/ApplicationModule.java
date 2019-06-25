package com.a1stopclick.dependencyinjection.modules;

import android.app.Application;
import android.content.Context;

import com.data.JobExecutor;
import com.a1stopclick.base.UIThread;
import com.data.account.repository.AccountDataRepository;
import com.data.album.repository.AlbumDataRepository;
import com.data.product.repository.ProductDataRepository;
import com.data.track.repository.TrackDataRepository;
import com.data.user.repository.UserDataRepository;
import com.data.video.repository.VideoDataRepository;
import com.domain.PostExecutionThread;
import com.domain.ThreadExecutor;
import com.domain.account.repository.AccountRepository;
import com.domain.album.repository.AlbumRepository;
import com.domain.product.repository.ProductRepository;
import com.domain.track.repository.TrackRepository;
import com.domain.user.repository.UserRepository;
import com.domain.video.repository.VideoRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
/*
 * Created by dendy-prtha on 26/02/2019.
 * Module for Application
 */

@Module
public class ApplicationModule {

    private final Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return application;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return application;
    }

    @Provides
    @Singleton
    ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
        return jobExecutor;
    }

    @Provides
    @Singleton
    PostExecutionThread providePostExecutionThread(UIThread uiThread) {
        return uiThread;
    }


    @Provides
    @Singleton
    UserRepository provideUserRepository(UserDataRepository userDataRepository) {
        return userDataRepository;
    }

    @Provides
    @Singleton
    AccountRepository provideAccountRepository(AccountDataRepository accountDataRepository) {
        return accountDataRepository;
    }

    @Provides
    @Singleton
    ProductRepository provideProductRepository(ProductDataRepository productDataRepository) {
        return productDataRepository;
    }

    @Provides
    @Singleton
    VideoRepository provideVideoRepository(VideoDataRepository videoDataRepository) {
        return videoDataRepository;
    }

    @Provides
    @Singleton
    TrackRepository provideTrackRepository(TrackDataRepository trackDataRepository) {
        return trackDataRepository;
    }

    @Provides
    @Singleton
    AlbumRepository provideAlbumRepository(AlbumDataRepository albumDataRepository) {
        return albumDataRepository;
    }
}
