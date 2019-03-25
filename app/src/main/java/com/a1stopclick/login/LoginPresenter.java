package com.a1stopclick.login;

import android.content.Context;
import android.util.Log;

import com.domain.DefaultObserver;
import com.domain.account.AccountResult;
import com.domain.account.interactor.SaveAccount;
import com.domain.user.LoginResult;
import com.domain.user.interactor.LocalLogin;
import com.domain.user.interactor.SocialLogin;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import javax.inject.Inject;

/*
 * Created by dendy-prtha on 08/03/2019.
 * LocalLogin Presenter
 */

public class LoginPresenter implements LoginContract.Presenter {

    private final Context context;

    private final LoginContract.View view;

    private LocalLogin localLogin;
    private SocialLogin socialLogin;
    private SaveAccount saveAccount;

    private static final String TAG = LoginPresenter.class.getSimpleName();

    @Inject
    public LoginPresenter(Context context, LoginContract.View view, LocalLogin localLogin,
                          SocialLogin socialLogin, SaveAccount saveAccount) {
        this.context = context;
        this.view = view;
        this.localLogin = localLogin;
        this.socialLogin = socialLogin;
        this.saveAccount = saveAccount;
    }

    @Override
    public void SignIn(String email, String password) {
        localLogin.execute(new DefaultObserver<LoginResult>() {

            @Override
            public void onNext(LoginResult result) {
                //TODO: need to record the user identity on share preference
                view.OnLoginSuccess();
                saveAccount(result.email, result.userProfile.name, result.providerId, result.provider,
                        result.userProfile.imageUrl, result.authToken);
            }

            @Override
            public void onError(Throwable er) {
                //TODO : need show error message based on error code from BE
                Log.d("SignIn", "onError: " + er.toString());
                view.OnLoginFailed(er.getMessage());
            }

        }, LocalLogin.Params.forLogin(email, password));
    }

    public void saveAccount(String email, String name, String provider_id, String provider
            , String avatarUrl, String authorization) {
        saveAccount.execute(new DefaultObserver<AccountResult>() {
            @Override
            public void onNext(AccountResult result) {
                Log.d("saveAccount", "account save "+ result.toString());
            }

            @Override
            public void onError(Throwable er) {
                //TODO : need show error message based on error code from BE
                Log.d("saveAccount", "onError: " + er.toString());

            }
        }, SaveAccount.Params.forSaveAccount(email, name, provider_id, provider, avatarUrl, authorization));
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


    public void HandleGoogleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            String email = account.getEmail();
            String idToken = account.getIdToken();
            Log.d(TAG, "token : " + idToken);
            account.getEmail();
            socialLogin.execute(new DefaultObserver<LoginResult>() {

                @Override
                public void onNext(LoginResult result) {
                    view.OnLoginSuccess();
                    saveAccount(result.email, result.userProfile.name, result.providerId, result.provider,
                            result.userProfile.imageUrl, result.authToken);
                }

                @Override
                public void onError(Throwable er) {
                    //TODO : need show error message based on error code from BE
                    Log.d("SignIn", "onError: " + er.toString());
                    view.OnLoginFailed(er.getMessage());
                }
            }, SocialLogin.Params.forLogin(email, idToken, LoginOption.GOOGLE));
            // Signed in successfully, show authenticated UI.
            //updateUI(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
            Log.w(TAG, "signInResult:failed message=" + e.getMessage());
            //updateUI(null);
        }
    }
}
