package com.a1stopclick.transaction;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;

import com.a1stopclick.base.BaseFragment;
import com.a1stopclick.transaction.invoice.FragmentInvoice;
import com.a1stopclick.transaction.purchase.FragmentPurchase;

import java.util.List;

/*
 * Created by dendy-prtha on 28/06/2019.
 * view pager adapter for Transaction tab
 */

public class ViewPagerTransactionAdapter extends FragmentStatePagerAdapter {

    List<BaseFragment> fragmentList;

    public ViewPagerTransactionAdapter(FragmentManager fm, List<BaseFragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;

    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentList.get(position).getFragmentTitle();
    }
}
