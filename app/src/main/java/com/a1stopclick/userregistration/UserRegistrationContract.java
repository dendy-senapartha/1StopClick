package com.a1stopclick.userregistration;


/*
 * Created by dendy-prtha on 13/03/2019.
 * Contract for user registration
 */

import com.a1stopclick.base.BaseContract;

public interface UserRegistrationContract {
    interface View extends BaseContract.BaseViewContract {
        void onRegisterUserSucces();
        void onRegisterUserFailed(String message);
    }

    interface Presenter extends BaseContract.BasePresenterContract {
        void registerUser(String email, Boolean emailVerified, String password, String provider,
                          String providerId, String name, String dob, String phone, String imageUrl);
    }

}
