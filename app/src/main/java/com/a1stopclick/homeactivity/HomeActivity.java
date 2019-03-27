package com.a1stopclick.homeactivity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;

import com.a1stopclick.R;
import com.a1stopclick.base.BaseActivity;
import com.a1stopclick.dependencyinjection.components.DaggerHomeActivityComponent;
import com.a1stopclick.dependencyinjection.components.HomeActivityComponent;
import com.a1stopclick.dependencyinjection.modules.HomeActivityModule;
import com.a1stopclick.login.LoginActivity;


import javax.inject.Inject;

import butterknife.BindView;

/*
 * Created by dendy-prtha on 14/03/2019.
 * Home Activity
 */

public class HomeActivity extends BaseActivity implements HomeContract.View {

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @BindView(R.id.nav_view)
    NavigationView navigationView;

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

        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }
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


    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.drag_and_drop_example:
                                //TODO :define the action for list_item
                                //startActivity(new Intent(MainActivity.this, DragActivity.class));
                                break;
                            case R.id.logout:
                                presenter.logOut();
                                break;
                            default:
                                break;
                        }
                        //close the navigation drawer when an item is selected
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        return true;
                    }
                }
        );
    }

    @Override
    public void onSuccessLogout() {
        finish();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
