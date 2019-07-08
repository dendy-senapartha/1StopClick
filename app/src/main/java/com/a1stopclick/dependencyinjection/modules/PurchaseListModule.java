package com.a1stopclick.dependencyinjection.modules;

import com.a1stopclick.dependencyinjection.PerActivity;
import com.a1stopclick.mylibrary.music.MusicLibraryContract;
import com.a1stopclick.mylibrary.music.MusicLibraryPresenter;
import com.a1stopclick.transaction.purchase.PurchaseContract;
import com.a1stopclick.transaction.purchase.PurchasePresenter;

import dagger.Module;
import dagger.Provides;
/*
 * Created by dendy-prtha on 18/04/2019.
 * purchase List DI Module
 */

@Module
public class PurchaseListModule {

    private PurchaseContract.View view;

    public PurchaseListModule(PurchaseContract.View view){
        this.view = view;
    }

    @Provides
    @PerActivity
    PurchaseContract.View provideView()
    {
        return view;
    }

    @Provides
    @PerActivity
    PurchaseContract.Presenter providePresenter(PurchasePresenter presenter)
    {
        return presenter;
    }
}
