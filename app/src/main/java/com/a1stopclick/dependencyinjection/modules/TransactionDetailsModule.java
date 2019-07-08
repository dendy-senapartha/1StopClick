package com.a1stopclick.dependencyinjection.modules;


import com.a1stopclick.dependencyinjection.PerActivity;
import com.a1stopclick.moviedetails.MovieDetailContract;
import com.a1stopclick.moviedetails.MovieDetailPresenter;
import com.a1stopclick.transactiondetails.TransactionDetailContract;
import com.a1stopclick.transactiondetails.TransactionDetailPresenter;

import dagger.Module;
import dagger.Provides;

/*
 * Created by dendy-prtha on 10/05/2019.
 * Movie Detail module
 */

@Module
public class TransactionDetailsModule {

    private TransactionDetailContract.View view;

    public TransactionDetailsModule(TransactionDetailContract.View view){
        this.view = view;
    }

    @Provides
    @PerActivity
    TransactionDetailContract.View provideView()
    {
        return view;
    }

    @Provides
    @PerActivity
    TransactionDetailContract.Presenter providePresenter(TransactionDetailPresenter presenter)
    {
        return presenter;
    }
}
