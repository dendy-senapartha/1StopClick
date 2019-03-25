package com.a1stopclick.login;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.a1stopclick.R;
import com.a1stopclick.base.BaseActivity;
import com.a1stopclick.dependencyinjection.components.DaggerLoginActivityComponent;
import com.a1stopclick.dependencyinjection.components.LoginActivityComponent;
import com.a1stopclick.dependencyinjection.modules.LoginActivityModule;
import com.a1stopclick.homeactivity.HomeActivity;
import com.a1stopclick.userregistration.UserRegistrationActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/*
 * Created by dendy-prtha on 08/03/2019.
 * login view
 */

public class LoginActivity extends BaseActivity implements LoginContract.View {

    private static final String TAG = LoginActivity.class.getSimpleName();
    private static final int RC_SIGN_IN = 2;

    @BindView(R.id.fieldEmail)
    EditText mEmailField;
    @BindView(R.id.fieldPassword)
    EditText mPasswordField;
    @BindView(R.id.buttonSignIn)
    Button mSignInButton;
    @BindView(R.id.buttonSignUp)
    Button mSignUpButton;
    @BindView(R.id.buttonGoogleSignin)
    SignInButton mGoogleSignIn;

    private LoginActivityComponent component;

    @Inject
    LoginContract.Presenter presenter;

    private GoogleSignInClient mGoogleSignInClient;

    @Override
    public int getLayout() {
        return R.layout.login_layout;
    }

    @Override
    public void init() {
        initComponent();
        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if (account != null) {
            GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken(getString(R.string.server_client_id))
                    .requestEmail()
                    .build();
            mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
            /*need to detect changes to a user's auth state that happen outside your app, such as access
            token or ID token revocation, or to perform cross-device sign-in,*/
            mGoogleSignInClient.silentSignIn().addOnCompleteListener(this, new OnCompleteListener<GoogleSignInAccount>() {
                @Override
                public void onComplete(@NonNull Task<GoogleSignInAccount> task) {
                    presenter.HandleGoogleSignInResult(task);
                }
            });
        } else {

        }
        //updateUI(account);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            presenter.HandleGoogleSignInResult(task);
        }
    }

    private void initComponent() {
        if (component == null) {
            component = DaggerLoginActivityComponent.builder()
                    .applicationComponent(getApplicationComponent())
                    .loginActivityModule(new LoginActivityModule(this))
                    .build();

        }
        component.inject(this);

        registerPresenter(presenter);
    }

    @Override
    public void OnLoginSuccess() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    @Override
    public void OnLoginFailed(String message) {

    }

    @Override
    public void OnSignOutSuccess() {

    }

    @Override
    public void OnSignOutFailed(String message) {

    }

    @OnClick(R.id.buttonGoogleSignin)
    public void onClickGoogleSignIn(View view) {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @OnClick(R.id.buttonSignIn)
    public void onClickSignIn(View view) {
        signIn();
    }

    @OnClick(R.id.buttonSignUp)
    public void onClickSignUp(View view) {
        Intent intent = new Intent(this, UserRegistrationActivity.class);
        startActivity(intent);
    }

    private void signIn() {
        Log.d(TAG, "signIn");
        if (!validateForm()) {
            return;
        }

        //showProgressDialog();
        String email = mEmailField.getText().toString();
        String password = mPasswordField.getText().toString();
        presenter.SignIn(email, password);
    }

    private boolean validateForm() {
        boolean result = true;
        if (TextUtils.isEmpty(mEmailField.getText().toString())) {
            mEmailField.setError("Required");
            result = false;
        } else {
            mEmailField.setError(null);
        }

        if (TextUtils.isEmpty(mPasswordField.getText().toString())) {
            mPasswordField.setError("Required");
            result = false;
        } else {
            mPasswordField.setError(null);
        }

        return result;
    }
}
