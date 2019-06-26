package com.a1stopclick.appssetting;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.a1stopclick.OneStopClickApplication;
import com.a1stopclick.R;
import com.a1stopclick.login.AccountOption;
import com.domain.DefaultObserver;
import com.domain.account.AccountResult;
import com.domain.account.interactor.DeleteAccount;
import com.domain.account.interactor.GetAccount;
import com.domain.product.interactor.FindMovieByTitle;
import com.domain.product.interactor.GetAllMovie;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import javax.inject.Inject;

/*
 * Created by dendy-prtha on 16/04/2019.
 * Presenter for Movie list
 */

public class AppsSettingPresenter implements AppsSettingContract.Presenter {

    private final String TAG = AppsSettingPresenter.class.getSimpleName();

    private final Context context;

    private final AppsSettingContract.View view;

    private final GetAccount getAccount;
    private final DeleteAccount deleteAccount;

    private AccountResult userSession = null;

    private GoogleSignInClient mGoogleSignInClient;

    @Inject
    public AppsSettingPresenter(Context context, AppsSettingContract.View view, GetAllMovie getAllMovie,
                                FindMovieByTitle findMovieByTitle, GetAccount getAccount, DeleteAccount deleteAccount) {
        this.context = context;
        this.view = view;
        this.getAccount = getAccount;
        this.deleteAccount = deleteAccount;
    }


    private void retrieveSession() {
        userSession = ((OneStopClickApplication) context).getSession();
    }

    @Override
    public void initPresenter() {
        retrieveSession();
    }

    @Override
    public void logOut() {
        view.setLoadingIndicator(true);
        getAccount.execute(new DefaultObserver<AccountResult>() {
            @Override
            public void onNext(AccountResult result) {
                if (result != null) {
                    switch (result.getProvider()) {
                        case AccountOption.GOOGLE:
                            logoutGoogleAccount();
                            break;
                        case AccountOption.LOCAL:
                            logoutLocalAccount();
                            break;
                        case AccountOption.FACEBOOK:
                            break;
                    }
                }
                Log.d("logOut", "account " + result.toString());

                    view.setLoadingIndicator(false);
            }

            @Override
            public void onError(Throwable er) {
                //TODO : need show error message based on error code from BE
                Log.d("logOut", "onError: " + er.toString());
            }
        });
    }


    private void logoutGoogleAccount() {
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(context);
        if (account != null) {
            GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken(context.getString(R.string.server_client_id))
                    .requestEmail()
                    .build();
            mGoogleSignInClient = GoogleSignIn.getClient(context, gso);

            /*need to detect changes to a user's auth state that happen outside your app, such as access
            token or ID token revocation, or to perform cross-device sign-in,*/
            mGoogleSignInClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    clearAccount();
                }
            });
        } else {

        }
    }

    private void logoutLocalAccount(){
        clearAccount();
    }

    private void clearAccount(){
        deleteAccount.execute(new DefaultObserver<Boolean>() {
            @Override
            public void onNext(Boolean result) {
                view.onSuccessLogout();
            }

            @Override
            public void onError(Throwable er) {
                //TODO : need show error message based on error code from BE
                Log.d("clearAccount", "onError: " + er.toString());

            }
        });
    }

    @Override
    public void onDestroy() {

    }
}
