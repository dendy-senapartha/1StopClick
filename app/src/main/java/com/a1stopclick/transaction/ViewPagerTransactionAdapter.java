package com.a1stopclick.transaction;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.a1stopclick.base.BaseFragment;
import com.a1stopclick.transaction.invoice.FragmentInvoice;
import com.a1stopclick.transaction.purchase.FragmentPurchase;

import java.util.List;

/*
 * Created by dendy-prtha on 28/06/2019.
 * view pager adapter for Transaction tab
 */

public class ViewPagerTransactionAdapter extends FragmentPagerAdapter {

    List<BaseFragment> fragmentList;

    public ViewPagerTransactionAdapter(FragmentManager fm, List<BaseFragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position == 0) {
            fragment = new FragmentInvoice();
        } else if (position == 1) {
            fragment = new FragmentPurchase();
        }
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        if (position == 0) {
            title = "Invoice";
        } else if (position == 1) {
            title = "Payment";
        }
        return fragmentList.get(position).getFragmentTitle();
    }
}
