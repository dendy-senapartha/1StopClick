package com.a1stopclick.login;

import android.content.Intent;
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
import com.a1stopclick.util.AndroidUtils;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.SignInButton;
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
    @BindView(R.id.progress_overlay)
    View progressOverlay;

    private LoginActivityComponent component;

    @Inject
    LoginContract.Presenter presenter;

    @Override
    public int getLayout() {
        return R.layout.login_layout;
    }

    @Override
    public void init() {
        initComponent();
        presenter.checkLastUsedAccount();
    }

    public void showLoading() {
        // Show progress overlay (with animation):
        AndroidUtils.animateView(progressOverlay, View.VISIBLE, 0.4f, 200);
    }

    public void hideLoading() {
        // Hide it (with animation):
        AndroidUtils.animateView(progressOverlay, View.GONE, 0, 200);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            presenter.handleGoogleSignInResult(task);
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
        finish();
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    @Override
    public void OnLoginFailed(String message) {

    }

    @OnClick(R.id.buttonGoogleSignin)
    public void onClickGoogleSignIn(View view) {
        Intent signInIntent = presenter.getGoogleSingInClient().getSignInIntent();
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

        String email = mEmailField.getText().toString();
        String password = mPasswordField.getText().toString();
        presenter.localSignIn(email, password);
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
