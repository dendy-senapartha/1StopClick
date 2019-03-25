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
    public void registerUser(String username, String password, String firstName,
                             String lastName, String dob, String phone, String profilePhoto) {
        registerUser.execute(new DefaultObserver<UserRegistrationResult>() {
            @Override
            public void onNext(UserRegistrationResult result) {
                view.onRegisterUserSucces();
            }

            @Override
            public void onError(Throwable e) {
                view.onRegisterUserFailed(e.getMessage());
            }
        }, RegisterUser.Params.forRegisterUser(username, password, firstName, lastName, dob, phone, profilePhoto));
    }
}
