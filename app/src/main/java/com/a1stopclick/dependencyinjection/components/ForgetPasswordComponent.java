package com.a1stopclick.dependencyinjection.components;

import com.a1stopclick.dependencyinjection.PerActivity;
import com.a1stopclick.dependencyinjection.modules.ForgetPasswordModule;
import com.a1stopclick.forgetpassword.ForgetPasswordActivity;

import dagger.Component;

/*
 * Created by dendy-prtha on 01/04/2019.
 * Forget password DI component
 */


@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ForgetPasswordModule.class})
public interface ForgetPasswordComponent {
    void inject(ForgetPasswordActivity activity);
}
