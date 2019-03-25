package com.a1stopclick.userregistration;


/*
 * Created by dendy-prtha on 13/03/2019.
 * Contract for user registration
 */

import com.a1stopclick.base.BaseContract;

public interface UserRegistrationContract {
    interface View {
        void onRegisterUserSucces();

        void onRegisterUserFailed(String message);
    }

    interface Presenter extends BaseContract.BasePresenterContract {
        void registerUser(String username, String password, String firstName,
                          String lastName, String dob, String phone, String profilePhoto);
    }

}
