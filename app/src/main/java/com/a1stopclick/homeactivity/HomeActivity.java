package com.a1stopclick.homeactivity;

import android.content.Intent;


import android.view.View;

import com.a1stopclick.R;
import com.a1stopclick.base.BaseActivity;
import com.a1stopclick.dependencyinjection.components.DaggerHomeActivityComponent;
import com.a1stopclick.dependencyinjection.components.HomeActivityComponent;
import com.a1stopclick.dependencyinjection.modules.HomeActivityModule;
import com.a1stopclick.login.LoginActivity;
import com.a1stopclick.util.AndroidUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.vlcplayer.VlcOptionsProvider;


import javax.inject.Inject;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.fragment.NavHostFragment;
import butterknife.BindView;
import androidx.navigation.ui.*;

/*
 * Created by dendy-prtha on 14/04/2019.
 * Home Activity
 */

public class HomeActivity extends BaseActivity implements HomeContract.View {

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @BindView(R.id.progress_overlay)
    View progressOverlay;

    @BindView(R.id.bottom_nav_view)
    BottomNavigationView bottomNavigationView;

    private HomeActivityComponent component;

    @Inject
    HomeContract.Presenter presenter;

    public static final int INDEX_MOVIE_LIST = 0;
    public static final int INDEX_EBOOK_LIST = INDEX_MOVIE_LIST + 1;

    @Override
    public int getLayout() {
        return R.layout.activity_home_layout;
    }

    @Override
    public void init() {
        initComponent();
        setupBottomNavigationView();
        configureVlcPlayer();

        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }
    }

    private void initComponent() {
        if (component == null) {
            component = DaggerHomeActivityComponent.builder()
                    .applicationComponent(getApplicationComponent())
                    .homeActivityModule(new HomeActivityModule(this))
                    .build();
        }
        component.inject(this);

        registerPresenter(presenter);
    }

    private void configureVlcPlayer() {
        VlcOptionsProvider.getInstance().setOptions((new VlcOptionsProvider.Builder(this)
                .setVerbose(true)
                .withSubtitleEncoding("KOI8-R").build()));
    }

    private void setupBottomNavigationView() {
        NavHostFragment hostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(bottomNavigationView, hostFragment.getNavController());
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                item -> {
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
        );
    }

    @Override
    public void onSuccessLogout() {
        finish();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void showLoading() {
        // Show progress overlay (with animation):
        AndroidUtils.animateView(progressOverlay, View.VISIBLE, 0.4f, 200);
    }

    public void hideLoading() {
        // Hide it (with animation):
        AndroidUtils.animateView(progressOverlay, View.GONE, 0, 200);
    }

}
