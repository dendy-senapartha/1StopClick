package com.a1stopclick.forgetpassword;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.a1stopclick.R;
import com.a1stopclick.base.BaseActivity;
import com.a1stopclick.dependencyinjection.components.DaggerForgetPasswordComponent;
import com.a1stopclick.dependencyinjection.components.ForgetPasswordComponent;
import com.a1stopclick.dependencyinjection.modules.ForgetPasswordModule;
import com.a1stopclick.util.AndroidUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

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

    ForgetPasswordComponent component;

    @Inject
    ForgetPasswordContract.Presenter presenter;

    @Override
    public int getLayout() {
        return R.layout.forget_password_layout;
    }

    @Override
    public void init() {
        initComponent();
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
        presenter.forgetPassword(mEmail.getText().toString());
    }

    @OnClick(R.id.buttonCancel)
    public void onClickCancel(View view) {
        onBackPressed();
    }
}
