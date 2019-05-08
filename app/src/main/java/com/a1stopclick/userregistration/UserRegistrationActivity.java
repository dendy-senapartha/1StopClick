package com.a1stopclick.userregistration;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
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
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

import static com.a1stopclick.util.AndroidUtils.isEmailValid;

/*
 * Created by dendy-prtha on 13/03/2019.
 * User registration activity
 */

public class UserRegistrationActivity extends BaseActivity implements UserRegistrationContract.View {

    public static final String TAG = UserRegistrationActivity.class.getSimpleName();

    private UserRegistrationComponent component;

    @BindView(R.id.parentLayout)
    RelativeLayout parentLayout;

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

    @BindView(R.id.email_text_input)
    TextInputLayout emailTextInputLayout;

    @BindView(R.id.progress_overlay)
    View progressOverlay;

    @Inject
    UserRegistrationContract.Presenter presenter;

    private DatePicker datePicker;
    private int year, month, day;
    private Calendar calendar;

    @Override
    public int getLayout() {
        return R.layout.user_registration_layout;
    }

    @Override
    public void init() {
        initComponent();

        configureInputForm();

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month + 1, day);
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    // TODO Auto-generated method stub
                    // arg1 = year
                    // arg2 = month
                    // arg3 = day
                    showDate(arg1, arg2 + 1, arg3);
                }
            };

    private void showDate(int year, int month, int day) {
        mDOBField.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }

    private void configureInputForm() {

        mEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!isEmailValid( mEmail.getText().toString().trim())) {
                    emailTextInputLayout.setErrorEnabled(true);
                    emailTextInputLayout.setError("invalid email address");
                    requestFocus(mEmail);
                } else {
                    emailTextInputLayout.setErrorEnabled(false);
                }
            }
        });

        mDOBField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(UserRegistrationActivity.this, myDateListener, calendar
                        .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
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

        if (!validateForm()) {
            return;
        }

        closeKeyboard();

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

    private boolean validateForm() {
        boolean result = true;
        if (TextUtils.isEmpty(mEmail.getText().toString())) {
            mEmail.setError("Email is Required");
            result = false;
        } else {
            mEmail.setError(null);
        }

        if (TextUtils.isEmpty(mPasswordField.getText().toString())) {
            mPasswordField.setError("Password is Required");
            result = false;
        } else {
            mPasswordField.setError(null);
        }

        if (TextUtils.isEmpty(name.getText().toString())) {
            name.setError("Name is Required");
            result = false;
        } else {
            name.setError(null);
        }

        if (TextUtils.isEmpty(mPhoneField.getText().toString())) {
            mPhoneField.setError("Phone Number is Required");
            result = false;
        } else {
            mPhoneField.setError(null);
        }
        return result;
    }

    private void closeKeyboard() {
        InputMethodManager inputManager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

    }

    @Override
    public void onRegisterUserFailed(String msg) {
        Snackbar snackbar = Snackbar
                .make(parentLayout, "Registration Failed", Snackbar.LENGTH_LONG);
        snackbar.show();
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
