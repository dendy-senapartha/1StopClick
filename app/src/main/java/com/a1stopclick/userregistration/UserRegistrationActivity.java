package com.a1stopclick.userregistration;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.a1stopclick.R;
import com.a1stopclick.base.BaseActivity;
import com.a1stopclick.dependencyinjection.components.DaggerUserRegistrationComponent;
import com.a1stopclick.dependencyinjection.components.UserRegistrationComponent;
import com.a1stopclick.dependencyinjection.modules.UserRegistrationModule;
import com.a1stopclick.homeactivity.HomeActivity;
import com.a1stopclick.login.AccountOption;
import com.a1stopclick.login.LoginActivity;
import com.a1stopclick.util.AndroidUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/*
 * Created by dendy-prtha on 13/03/2019.
 * User registration activity
 */

public class UserRegistrationActivity extends BaseActivity implements UserRegistrationContract.View {

    public static final String TAG = UserRegistrationActivity.class.getSimpleName();

    private UserRegistrationComponent component;

    @BindView(R.id.fieldEmail)
    EditText mEmail;
    @BindView(R.id.fieldPassword)
    EditText mPasswordField;
    @BindView(R.id.fieldFirstName)
    EditText name;

    @BindView(R.id.fieldDob)
    EditText mDOBField;
    @BindView(R.id.fieldPhone)
    EditText mPhoneField;
    @BindView(R.id.fieldPhotoProfile)
    EditText mPhotoField;
    @BindView(R.id.buttonSubmit)
    Button mSubmitButton;
    @BindView(R.id.buttonCancel)
    Button mCancelButton;

    @BindView(R.id.progress_overlay)
    View progressOverlay;

    @Inject
    UserRegistrationContract.Presenter presenter;

    @Override
    public int getLayout() {
        return R.layout.user_registration_layout;
    }

    @Override
    public void init() {
        initComponent();
    }

    private void initComponent() {
        if (component == null) {
            component = DaggerUserRegistrationComponent.builder()
                    .applicationComponent(getApplicationComponent())
                    .userRegistrationModule(new UserRegistrationModule(this))
                    .build();
        }
        component.inject(this);

        registerPresenter(presenter);
    }

    @OnClick(R.id.buttonCancel)
    public void onClickButtonCancel(View view) {
        finish();
    }

    @OnClick(R.id.buttonSubmit)
    public void onClickButtonSubmit(View view) {
        String email = mEmail.getText().toString();
        String password = mPasswordField.getText().toString();
        String name = this.name.getText().toString();

        String dob = mDOBField.getText().toString();
        String phone = mPhoneField.getText().toString();
        String photoProfile = mPhotoField.getText().toString();

        presenter.registerUser(email, false, password, AccountOption.LOCAL, null,
                name, dob, phone, photoProfile);
    }

    @Override
    public void onRegisterUserSucces() {
        finish();
        Toast.makeText(getApplicationContext(), "Registration Success. Please Login using your created account.", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void onRegisterUserFailed(String msg) {

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
