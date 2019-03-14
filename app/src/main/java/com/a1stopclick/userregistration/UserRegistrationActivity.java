package com.a1stopclick.userregistration;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.a1stopclick.R;
import com.a1stopclick.base.BaseActivity;
import com.a1stopclick.dependencyinjection.components.DaggerUserRegistrationComponent;
import com.a1stopclick.dependencyinjection.components.UserRegistrationComponent;
import com.a1stopclick.dependencyinjection.modules.UserRegistrationModule;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/*
 * Created by dendy-prtha on 13/03/2019.
 * User registration activity
 */

public class UserRegistrationActivity extends BaseActivity implements UserRegistrationContract.View {

    public static final String TAG = UserRegistrationContract.class.getSimpleName();

    private UserRegistrationComponent component;

    @BindView(R.id.fieldEmail)
    EditText mUsername;
    @BindView(R.id.fieldPassword)
    EditText mPasswordField;
    @BindView(R.id.fieldFirstName)
    EditText mFirstNameField;
    @BindView(R.id.fieldLastName)
    EditText mLastNameField;
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
        String username = mUsername.getText().toString();
        String password = mPasswordField.getText().toString();
        String firstname = mFirstNameField.getText().toString();
        String lastname = mLastNameField.getText().toString();
        String dob = mDOBField.getText().toString();
        String phone = mPhoneField.getText().toString();
        String photoProfile = mPhotoField.getText().toString();

        presenter.registerUser(username, password, firstname, lastname, dob, phone, photoProfile);
    }

}
