package com.a1stopclick.home;


/*
 * Created by dendy-prtha on 02/04/2019.
 * Fragment for Ebook list
 */

import com.a1stopclick.R;
import com.a1stopclick.base.BaseFragment;

public class FragmentEbookList extends BaseFragment {

    public static FragmentEbookList newInstance() {
        final FragmentEbookList mf = new FragmentEbookList();
        mf.setFragmentTitle("E-Book");
        return mf;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_ebook_list;
    }

    @Override
    protected void init() {

    }
}
