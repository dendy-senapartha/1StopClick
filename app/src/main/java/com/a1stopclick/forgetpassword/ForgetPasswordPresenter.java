package com.a1stopclick.forgetpassword;

import android.content.Context;
import android.util.Log;

import com.domain.DefaultObserver;
import com.domain.user.ForgetPasswordResult;
import com.domain.user.interactor.ForgetPassword;

import javax.inject.Inject;

/*
 * Created by dendy-prtha on 01/04/2019.
 * Forget Password presenter
 */

public class ForgetPasswordPresenter implements ForgetPasswordContract.Presenter {

    private final Context context;

    private final ForgetPasswordContract.View view;

    private final ForgetPassword forgetPassword;

    @Inject
    public ForgetPasswordPresenter(Context context, ForgetPasswordContract.View view,
                                   ForgetPassword forgetPassword) {

        this.context = context;
        this.view = view;
        this.forgetPassword = forgetPassword;
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void forgetPassword(String email) {
        view.showProgress();
        forgetPassword.execute(new DefaultObserver<ForgetPasswordResult>() {
            @Override
            public void onNext(ForgetPasswordResult result) {
                if(result.status)
                {
                    view.onForgetPasswordSuccess();
                }
                else
                {
                    view.onForgetPasswordFailed(result.errorMessage);
                }
                Log.d("forgetPassword ", "onNext: "+result.status);
                view.dismissProgress();
            }

            @Override
            public void onError(Throwable er) {
                //TODO : need show error message based on error code from BE
                Log.d("forgetPassword", "onError: " + er.toString());
                view.dismissProgress();
                view.onError(er.getMessage());
            }
        }, ForgetPassword.Params.forgetPassword(email));
    }
}
