package com.a1stopclick.homeactivity;

import android.content.Context;

import javax.inject.Inject;

/*
 * Created by dendy-prtha on 14/03/2019.
 * Home
 */

public class HomePresenter implements HomeContract.Presenter {

    private final Context context;

    private final HomeContract.View view;

    @Inject
    public HomePresenter(Context context, HomeContract.View view) {

        this.context = context;
        this.view = view;
    }

    @Override
    public void onDestroy() {

    }
}
