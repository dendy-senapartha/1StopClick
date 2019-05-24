package com.a1stopclick.login;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.a1stopclick.R;
import com.a1stopclick.base.BaseActivity;
import com.a1stopclick.dependencyinjection.components.DaggerLoginActivityComponent;
import com.a1stopclick.dependencyinjection.components.LoginActivityComponent;
import com.a1stopclick.dependencyinjection.modules.LoginActivityModule;
import com.a1stopclick.forgetpassword.ForgetPasswordActivity;
import com.a1stopclick.homeactivity.HomeActivity;
import com.a1stopclick.userregistration.UserRegistrationActivity;
import com.a1stopclick.util.AndroidUtils;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

import static com.a1stopclick.util.AndroidUtils.isEmailValid;

/*
 * Created by dendy-prtha on 08/03/2019.
 * login view
 */

public class LoginActivity extends BaseActivity implements LoginContract.View {

    private static final String TAG = LoginActivity.class.getSimpleName();
    private static final int RC_SIGN_IN = 2;

    @BindView(R.id.parentLayout)
    RelativeLayout parentLayout;

    @BindView(R.id.fieldEmail)
    EditText mEmailField;
    @BindView(R.id.fieldPassword)
    EditText mPasswordField;
    @BindView(R.id.buttonSignIn)
    Button mSignInButton;
    @BindView(R.id.buttonGoogleSignin)
    SignInButton mGoogleSignIn;
    @BindView(R.id.progress_overlay)
    View progressOverlay;

    @BindView(R.id.email_text_input)
    TextInputLayout emailTextInputLayout;

    @BindView(R.id.txtSignUp)
    TextView mTxtSignUp;

    @BindView(R.id.txtForgetPassword)
    TextView mTxtForgetPassword;

    private LoginActivityComponent component;

    @Inject
    LoginContract.Presenter presenter;

    @Override
    public int getLayout() {
        return R.layout.activity_login_layout;
    }

    @Override
    public void init() {
        initComponent();
        presenter.checkLastUsedAccount();
        configureInputForm();
    }

    private void configureInputForm() {
        mEmailField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!isEmailValid( mEmailField.getText().toString().trim())) {
                    emailTextInputLayout.setErrorEnabled(true);
                    emailTextInputLayout.setError("invalid email address");
                    requestFocus(mEmailField);
                } else {
                    emailTextInputLayout.setErrorEnabled(false);
                }
            }
        });
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
        Snackbar snackbar = Snackbar
                .make(parentLayout, "Username or Password is wrong", Snackbar.LENGTH_LONG);
        snackbar.show();
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

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    @OnClick(R.id.txtForgetPassword)
    public void onClickForgetPassword(View view) {
        Intent intent = new Intent(this, ForgetPasswordActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.txtSignUp)
    public void onClickTxtSignUp(View view) {
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
        closeKeyboard();
    }

    private void closeKeyboard() {
        InputMethodManager inputManager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

    }

    private boolean validateForm() {
        boolean result = true;
        if (TextUtils.isEmpty(mEmailField.getText().toString())) {
            mEmailField.setError("Email is Required");
            result = false;
        } else {
            mEmailField.setError(null);
        }

        if (TextUtils.isEmpty(mPasswordField.getText().toString())) {
            mPasswordField.setError("Password is Required");
            result = false;
        } else {
            mPasswordField.setError(null);
        }

        return result;
    }

    @Override
    public void showProgress() {
        // Show progress overlay (with animation):
        AndroidUtils.animateView(progressOverlay, View.VISIBLE, 0.4f, 200);
    }

    @Override
    public void dismissProgress() {
        // Hide it (with animation):
        AndroidUtils.animateView(progressOverlay, View.GONE, 0, 200);
    }

    @Override
    public void onError(String errorMsg) {

    }
}
