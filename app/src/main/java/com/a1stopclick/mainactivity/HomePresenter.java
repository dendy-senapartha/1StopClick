package com.a1stopclick.mainactivity;

import android.content.Context;

import android.util.Log;

import com.a1stopclick.R;
import com.a1stopclick.login.AccountOption;
import com.a1stopclick.mainactivity.HomeContract;
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

import androidx.annotation.NonNull;

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
}
