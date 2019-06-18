package com.a1stopclick.login;


/*
 * Created by dendy-prtha on 08/03/2019.
 * LocalLogin view and Presenter contract
 */

import com.a1stopclick.base.BaseContract;
import com.domain.account.AccountResult;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.Task;

public interface LoginContract {

    interface View extends BaseContract.BaseViewContract{
        void OnLoginSuccess(AccountResult result);
        void OnLoginFailed(String message);
    }

    interface Presenter extends BaseContract.BasePresenterContract{
        void localSignIn(String email, String password);
        void handleGoogleSignInResult(Task<GoogleSignInAccount> completedTask);
        void checkLastUsedAccount();
        GoogleSignInClient getGoogleSingInClient();
    }

}
