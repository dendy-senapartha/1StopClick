package com.a1stopclick.home;


import android.os.Handler;
import android.widget.Toast;

import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;


import com.a1stopclick.R;
import com.a1stopclick.base.BaseFragment;

import com.a1stopclick.base.fragmentback.BackFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;


import butterknife.BindView;

/*
 * Created by dendy-prtha on 26/04/2019.
 * work as container fragment for movies, musics, and ebooks
 */

public class HomeFragment extends BaseFragment implements BackFragment {

    private final String TAG = HomeFragment.class.getSimpleName();

    @BindView(R.id.bottom_nav_view)
    BottomNavigationView bottomNavigationView;

    boolean doubleBackToExitPressedOnce = false;

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

    public boolean onBackPressed() {
        // return true if you want to consume back-pressed event
        if (doubleBackToExitPressedOnce) {
            requireActivity().finishAffinity();
            System.exit(0);
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(requireActivity(), "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(() -> doubleBackToExitPressedOnce = false
                , 2000);
        return true;
    }

    public int getBackPriority() {
        // use apropriate priority here
        return NORMAL_BACK_PRIORITY;
    }

}
