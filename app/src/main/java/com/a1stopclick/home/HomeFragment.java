package com.a1stopclick.home;


import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;


import com.a1stopclick.R;
import com.a1stopclick.base.BaseFragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;


import butterknife.BindView;

/*
 * Created by dendy-prtha on 26/04/2019.
 * work as container fragment for movies, musics, and ebooks
 */

public class HomeFragment extends BaseFragment {

    private final String TAG = HomeFragment.class.getSimpleName();

    @BindView(R.id.bottom_nav_view)
    BottomNavigationView bottomNavigationView;

    @Override
    protected int getLayout() {
        return R.layout.fragment_home_layout;
    }

    private void setupBottomNavigationView() {
        NavHostFragment hostFragment = (NavHostFragment) getChildFragmentManager().findFragmentById(R.id.nav_host_home_fragment);
        NavigationUI.setupWithNavController(bottomNavigationView, hostFragment.getNavController());
        getBaseActivity().getSupportActionBar().setTitle("Home");
    }

    @Override
    protected void init() {
        setupBottomNavigationView();
    }

}
