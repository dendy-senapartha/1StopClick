package com.a1stopclick.login;

import android.content.Context;

import android.util.Log;

import com.a1stopclick.OneStopClickApplication;
import com.a1stopclick.R;
import com.domain.DefaultObserver;
import com.domain.account.AccountResult;
import com.domain.account.interactor.GetAccount;
import com.domain.account.interactor.SaveAccount;
import com.domain.user.LoginResult;

import com.domain.user.interactor.Login;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import javax.inject.Inject;

import androidx.annotation.NonNull;

/*
 * Created by dendy-prtha on 08/03/2019.
 * LocalLogin Presenter
 */

public class LoginPresenter implements LoginContract.Presenter {

    private final Context context;

    private final LoginContract.View view;

    private Login login;
    private SaveAccount saveAccount;

    private GetAccount getAccount;

    private static final String TAG = LoginPresenter.class.getSimpleName();

    private GoogleSignInClient mGoogleSignInClient;

    @Inject
    public LoginPresenter(Context context, LoginContract.View view, Login login,
                          SaveAccount saveAccount, GetAccount getAccount) {
        this.context = context;
        this.view = view;
        this.login = login;
        this.saveAccount = saveAccount;
        this.getAccount = getAccount;

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(context.getString(R.string.server_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(context, gso);
    }

    @Override
    public void localSignIn(String email, String password) {
        view.showProgress();
        login.execute(new DefaultObserver<LoginResult>() {

            @Override
            public void onNext(LoginResult result) {
                saveAccount(result.uid , result.email, result.userProfile.name, result.providerId, result.provider,
                        result.userProfile.imageUrl, result.authToken);
            }

            @Override
            public void onError(Throwable er) {
                //TODO : need show error message based on error code from BE
                Log.d("localSignIn", "onError: " + er.toString());
                view.OnLoginFailed(er.getMessage());
                view.dismissProgress();
            }

            @Override
            public void onComplete() {
                view.dismissProgress();
            }
        }, Login.Params.forLogin(email, password, null, AccountOption.LOCAL));
    }

    public void saveAccount(String uid, String email, String name, String provider_id, String provider
            , String avatarUrl, String authorization) {
        saveAccount.execute(new DefaultObserver<AccountResult>() {
            @Override
            public void onNext(AccountResult result) {
                Log.d("saveAccount", "account save " + result.toString());
                view.OnLoginSuccess(result);
            }

            @Override
            public void onError(Throwable er) {
                //TODO : need show error message based on error code from BE
                Log.d("saveAccount", "onError: " + er.toString());

            }
        }, SaveAccount.Params.forSaveAccount(uid , email, name, provider_id, provider, avatarUrl, authorization));
    }

    @Override
    public void checkLastUsedAccount() {
        view.showProgress();
        getAccount.execute(new DefaultObserver<AccountResult>() {
            @Override
            public void onNext(AccountResult result) {
                String provider = result.getProvider();
                Log.d("checkLastUsedAccount", "get orderId " + provider);

                if (provider != null) {
                    switch (provider) {
                        case AccountOption.GOOGLE:
                            googleAccountLoginChecker();
                            break;
                        case AccountOption.LOCAL:
                            if (result.getAuthorization() != null) {
                                view.OnLoginSuccess(result);
                                //localAccountLoginChecker(result.getAuthorization());
                            }
                            break;
                        case AccountOption.FACEBOOK:
                            break;
                    }
                }
            }

            @Override
            public void onError(Throwable er) {
                //TODO : need show error message based on error code from BE
                Log.d("checkLastUsedAccount", "onError: " + er.toString());
                view.dismissProgress();
            }

            @Override
            public void onComplete() {
                view.dismissProgress();
            }
        });

    }

    @Override
    public GoogleSignInClient getGoogleSingInClient() {
        return mGoogleSignInClient;
    }

    private void googleAccountLoginChecker() {
        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(context);

        if (account != null) {
            /*need to detect changes to a user's auth state that happen outside your app, such as access
            token or ID token revocation, or to perform cross-device sign-in,*/
            mGoogleSignInClient.silentSignIn().addOnCompleteListener(new OnCompleteListener<GoogleSignInAccount>() {
                @Override
                public void onComplete(@NonNull Task<GoogleSignInAccount> task) {
                    handleGoogleSignInResult(task);
                }
            });
        } else {

        }
    }

    /*Do local login checking by check if authorization value is not null*/
    private void localAccountLoginChecker(String authorization) {
        if (!authorization.isEmpty()) {
            //saveAccount(result.getEmail(),result.getName(),result.getProvider_id(),result.getProvider(),result.getAvatarUrl(),result.getAuthorization());
        } else {

        }
    }

    @Override
    public void onDestroy() {

    }

    public void handleGoogleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            String email = account.getEmail();
            String idToken = account.getIdToken();
            Log.d(TAG, "token : " + idToken);
            account.getEmail();
            view.showProgress();
            login.execute(new DefaultObserver<LoginResult>() {

                @Override
                public void onNext(LoginResult result) {
                    saveAccount(result.uid, result.email, result.userProfile.name, result.providerId, result.provider,
                            result.userProfile.imageUrl, result.authToken);
                }

                @Override
                public void onError(Throwable er) {
                    //TODO : need show error message based on error code from BE
                    Log.d("localSignIn", "onError: " + er.toString());
                    view.dismissProgress();
                    view.OnLoginFailed(er.getMessage());
                }

                @Override
                public void onComplete() {
                    view.dismissProgress();
                }
            }, Login.Params.forLogin(email, null, idToken, AccountOption.GOOGLE));
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
