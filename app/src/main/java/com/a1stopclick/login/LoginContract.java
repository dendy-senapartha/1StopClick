package com.a1stopclick.login;


/*
 * Created by dendy-prtha on 08/03/2019.
 * Login view and Presenter contract
 */

import com.a1stopclick.base.BaseContract;

public interface LoginContract {

    interface View{
        void OnSignInSuccess();
        void OnSignOutSuccess();
    }

    interface Presenter extends BaseContract.BasePresenterContract{
        void SignIn(String email, String password);
        void SignUp(String email, String password);
        void SignOut();
        void IsSignIn();
    }

}
