package com.a1stopclick.forgetpassword;

import com.a1stopclick.base.BaseContract;

/*
 * Created by dendy-prtha on 01/04/2019.
 * View Presenter contract for forget password
 */

public interface ForgetPasswordContract {
    interface View extends BaseContract.BaseViewContract {
        void onForgetPasswordSuccess();
        void onForgetPasswordFailed(String errMessage);
    }

    interface Presenter extends BaseContract.BasePresenterContract {
        void forgetPassword(String email);
    }
}
