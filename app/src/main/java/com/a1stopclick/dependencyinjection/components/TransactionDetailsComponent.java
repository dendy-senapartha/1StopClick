package com.a1stopclick.dependencyinjection.components;

import com.a1stopclick.dependencyinjection.PerActivity;
import com.a1stopclick.dependencyinjection.modules.MovieDetailsModule;
import com.a1stopclick.dependencyinjection.modules.TransactionDetailsModule;
import com.a1stopclick.moviedetails.MovieDetailActivity;
import com.a1stopclick.transactiondetails.TransactionDetailActivity;

import dagger.Component;

/*
 * Created by dendy-prtha on 10/05/2019.
 * Movie Detail component
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {TransactionDetailsModule.class})
public interface TransactionDetailsComponent {
    void inject(TransactionDetailActivity activity);
}
