package com.a1stopclick.userregistration;

import android.content.Context;

import com.domain.DefaultObserver;
import com.domain.user.UserRegistrationResult;
import com.domain.user.interactor.RegisterUser;

import javax.inject.Inject;

/*
 * Created by dendy-prtha on 13/03/2019.
 * user registration presenter
 */

public class UserRegistrationPresenter implements UserRegistrationContract.Presenter {

    private final Context context;

    private final UserRegistrationContract.View view;

    private final RegisterUser registerUser;

    @Inject
    public UserRegistrationPresenter(Context context, UserRegistrationContract.View view, RegisterUser registerUser) {
        this.context = context;
        this.view = view;
        this.registerUser = registerUser;
    }

    @Override
    public void onDestroy() {

    }


    @Override
    public void registerUser(String email, Boolean emailVerified, String password, String provider,
                             String providerId, String name, String dob, String phone, String imageUrl) {
        view.showProgress();
        registerUser.execute(new DefaultObserver<UserRegistrationResult>() {
            @Override
            public void onNext(UserRegistrationResult result) {
                view.onRegisterUserSucces();
            }

            @Override
            public void onError(Throwable e) {
                view.onRegisterUserFailed(e.getMessage());
                view.dismissProgress();
            }

            @Override
            public void onComplete() {
                view.dismissProgress();
            }
        }, RegisterUser.Params.forRegisterUser(email, false, password, provider, null,
                name, dob, phone, imageUrl));
    }
}
