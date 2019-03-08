package com.a1stopclick.login;

import android.content.Context;

import javax.inject.Inject;

/*
 * Created by dendy-prtha on 08/03/2019.
 * Login Presenter
 */

public class LoginPresenter implements LoginContract.Presenter {

    private final Context context;

    private final LoginContract.View view;

    @Inject
    public LoginPresenter(Context context, LoginContract.View view) {
        this.context = context;
        this.view = view;
    }

    @Override
    public void SignIn(String email, String password) {

    }

    @Override
    public void SignUp(String email, String password) {

    }

    @Override
    public void SignOut() {

    }

    @Override
    public void IsSignIn() {

    }

    @Override
    public void onDestroy() {

    }
}
