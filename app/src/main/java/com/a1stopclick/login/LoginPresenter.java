package com.a1stopclick.login;

import android.content.Context;
import android.util.Log;

import com.domain.DefaultObserver;
import com.domain.user.LoginResult;
import com.domain.user.interactor.Login;

import javax.inject.Inject;

/*
 * Created by dendy-prtha on 08/03/2019.
 * Login Presenter
 */

public class LoginPresenter implements LoginContract.Presenter {

    private final Context context;

    private final LoginContract.View view;

    private Login login;

    @Inject
    public LoginPresenter(Context context, LoginContract.View view, Login login) {
        this.context = context;
        this.view = view;
        this.login = login;
    }

    @Override
    public void SignIn(String email, String password) {
        login.execute(new DefaultObserver<LoginResult>() {

            @Override
            public void onNext(LoginResult result) {
                //TODO: need to record the user identity on share preference
                view.OnLoginSuccess();
            }

            @Override
            public void onError(Throwable er) {
                //TODO : need show error message based on error code from BE
                Log.d("SignIn", "onError: " + er.toString());
                view.OnLoginFailed(er.getMessage());
            }

        }, Login.Params.forLogin(email, password));
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
