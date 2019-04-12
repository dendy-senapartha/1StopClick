package com.a1stopclick.homeactivity;


import com.a1stopclick.base.BaseFragment;

import java.util.List;
import java.util.Vector;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * Created by dendy-prtha on 14/04/2019.
 * Home Page Adapter for page view
 */

public class HomePageAdapter extends FragmentPagerAdapter {

    List<BaseFragment> fragments;

    public HomePageAdapter(FragmentManager fm) {
        super(fm);
        fragments = new Vector<>();
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragments.get(position).getFragmentTitle();
    }


    public void addFragment(int index, BaseFragment fragment) {
        fragments.add(index, fragment);
    }
}
