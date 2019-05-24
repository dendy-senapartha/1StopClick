package com.a1stopclick.forgetpassword;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.a1stopclick.R;
import com.a1stopclick.base.BaseActivity;
import com.a1stopclick.dependencyinjection.components.DaggerForgetPasswordComponent;
import com.a1stopclick.dependencyinjection.components.ForgetPasswordComponent;
import com.a1stopclick.dependencyinjection.modules.ForgetPasswordModule;
import com.a1stopclick.util.AndroidUtils;
import com.google.android.material.textfield.TextInputLayout;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

import static com.a1stopclick.util.AndroidUtils.isEmailValid;

/*
 * Created by dendy-prtha on 01/04/2019.
 * Forget Password activity
 */

public class ForgetPasswordActivity extends BaseActivity implements ForgetPasswordContract.View {

    public static final String TAG = ForgetPasswordActivity.class.getSimpleName();

    @BindView(R.id.fieldEmail)
    EditText mEmail;

    @BindView(R.id.buttonSubmit)
    Button mSubmitButton;
    @BindView(R.id.buttonCancel)
    Button mCancelButton;

    @BindView(R.id.progress_overlay)
    View progressOverlay;

    @BindView(R.id.email_text_input)
    TextInputLayout emailTextInputLayout;

    ForgetPasswordComponent component;

    @Inject
    ForgetPasswordContract.Presenter presenter;

    @Override
    public int getLayout() {
        return R.layout.activity_forget_password_layout;
    }

    @Override
    public void init() {
        initComponent();
        configureInputForm();
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
    }

    private void closeKeyboard() {
        InputMethodManager inputManager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private boolean validateForm() {
        boolean result = true;
        if (TextUtils.isEmpty(mEmail.getText().toString())) {
            mEmail.setError("Email is Required");
            result = false;
        } else {
            mEmail.setError(null);
        }

        return result;
    }

    private void initComponent() {
        if (component == null) {

            component = DaggerForgetPasswordComponent.builder()
                    .applicationComponent(getApplicationComponent())
                    .forgetPasswordModule(new ForgetPasswordModule(this))
                    .build();
        }
        component.inject(this);

        registerPresenter(presenter);
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
        Toast.makeText(getApplicationContext(), errorMsg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onForgetPasswordSuccess() {
        Toast.makeText(getApplicationContext(), getString(R.string.text_password_recovery_success), Toast.LENGTH_LONG).show();
        onBackPressed();
    }

    @Override
    public void onForgetPasswordFailed(String errMessage) {
        Toast.makeText(getApplicationContext(), errMessage, Toast.LENGTH_LONG).show();
    }

    @OnClick(R.id.buttonSubmit)
    public void onClickForgetPassword(View view) {
        if(!validateForm())
        {
            return;
        }

        closeKeyboard();
        presenter.forgetPassword(mEmail.getText().toString());
    }

    @OnClick(R.id.buttonCancel)
    public void onClickCancel(View view) {
        onBackPressed();
    }
}
