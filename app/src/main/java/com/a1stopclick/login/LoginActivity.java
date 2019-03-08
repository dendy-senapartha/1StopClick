package com.a1stopclick.login;

import android.widget.Button;
import android.widget.EditText;

import com.a1stopclick.R;
import com.a1stopclick.base.BaseActivity;
import com.a1stopclick.dependencyinjection.components.DaggerLoginActivityComponent;
import com.a1stopclick.dependencyinjection.components.LoginActivityComponent;
import com.a1stopclick.dependencyinjection.modules.LoginActivityModule;

import javax.inject.Inject;

import butterknife.BindView;

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
    public void OnSignInSuccess() {

    }

    @Override
    public void OnSignOutSuccess() {

    }
}
