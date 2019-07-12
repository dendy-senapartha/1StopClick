package com.a1stopclick.transaction;


import android.os.Handler;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.a1stopclick.R;
import com.a1stopclick.base.BaseFragment;
import com.a1stopclick.base.fragmentback.BackFragment;
import com.a1stopclick.transaction.invoice.FragmentInvoice;
import com.a1stopclick.transaction.purchase.FragmentPurchase;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/*
 * Created by dendy-prtha on 26/04/2019.
 * work as container fragment for invoice and purchase feature
 */

public class TransactionFragment extends BaseFragment implements BackFragment {

    private final String TAG = TransactionFragment.class.getSimpleName();

    @BindView(R.id.transactionTab)
    TabLayout tabLayout;

    @BindView(R.id.transactionViewPager)
    ViewPager viewPager;

    ViewPagerTransactionAdapter viewPagerTransactionAdapter;

    boolean doubleBackToExitPressedOnce = false;

    @Override
    protected int getLayout() {
        return R.layout.fragment_transaction;
    }

    @Override
    protected void init() {
        prepareTabTransaction();
        getBaseActivity().getSupportActionBar().setTitle("Transaction");
    }

    private void prepareTabTransaction() {
        List<BaseFragment> fragmentList = new ArrayList<>();
        fragmentList.add(new FragmentInvoice());
        fragmentList.add(new FragmentPurchase());

        viewPagerTransactionAdapter = new ViewPagerTransactionAdapter(
                getFragmentManager(), fragmentList);
        viewPager.setAdapter(viewPagerTransactionAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    public void onDestroyView() {
        super.onDestroyView();
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
