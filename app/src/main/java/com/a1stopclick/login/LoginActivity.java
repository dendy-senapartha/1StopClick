package com.a1stopclick.login;

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

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/*
 * Created by dendy-prtha on 08/03/2019.
 * login view
 */

public class LoginActivity extends BaseActivity implements LoginContract.View {

    private static final String TAG = LoginActivity.class.getSimpleName();

    @BindView(R.id.fieldEmail)
    EditText mEmailField;
    @BindView(R.id.fieldPassword)
    EditText mPasswordField;
    @BindView(R.id.buttonSignIn)
    Button mSignInButton;
    @BindView(R.id.buttonSignUp)
    Button mSignUpButton;

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

    @OnClick(R.id.buttonSignIn)
    public void onClickSignIn(View view) {
        signIn();
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
