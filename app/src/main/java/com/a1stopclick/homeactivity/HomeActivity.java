package com.a1stopclick.homeactivity;

import com.a1stopclick.R;
import com.a1stopclick.base.BaseActivity;
import com.a1stopclick.dependencyinjection.components.DaggerHomeActivityComponent;
import com.a1stopclick.dependencyinjection.components.HomeActivityComponent;
import com.a1stopclick.dependencyinjection.modules.HomeActivityModule;


import javax.inject.Inject;

/*
 * Created by dendy-prtha on 14/03/2019.
 * Home Activity
 */

public class HomeActivity extends BaseActivity implements HomeContract.View {

    private HomeActivityComponent component;

    @Inject
    HomeContract.Presenter presenter;

    @Override
    public int getLayout() {
        return R.layout.home_activity_layout;
    }

    @Override
    public void init() {
        initComponent();
    }

    private void initComponent() {
        if (component == null) {
            component= DaggerHomeActivityComponent.builder()
                    .applicationComponent(getApplicationComponent())
                    .homeActivityModule(new HomeActivityModule(this))
                    .build();
        }
        component.inject(this);

        registerPresenter(presenter);
    }
}
