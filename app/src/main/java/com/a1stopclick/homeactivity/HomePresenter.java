package com.a1stopclick.homeactivity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.a1stopclick.R;
import com.a1stopclick.login.AccountOption;
import com.domain.DefaultObserver;
import com.domain.account.AccountResult;
import com.domain.account.interactor.DeleteAccount;
import com.domain.account.interactor.GetAccount;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import javax.inject.Inject;

/*
 * Created by dendy-prtha on 14/03/2019.
 * Home
 */

public class HomePresenter implements HomeContract.Presenter {

    private final Context context;

    private final HomeContract.View view;

    private final GetAccount getAccount;
    private final DeleteAccount deleteAccount;

    private GoogleSignInClient mGoogleSignInClient;

    @Inject
    public HomePresenter(Context context, HomeContract.View view, GetAccount getAccount, DeleteAccount deleteAccount) {

        this.context = context;
        this.view = view;
        this.getAccount = getAccount;
        this.deleteAccount = deleteAccount;
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void logOut() {
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
            }

            @Override
            public void onError(Throwable er) {
                //TODO : need show error message based on error code from BE
                Log.d("logOut", "onError: " + er.toString());

            }
        });
    }

    private void logoutLocalAccount(){
        clearAccount();
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
}
