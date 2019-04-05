package com.a1stopclick.homeactivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.a1stopclick.R;
import com.a1stopclick.base.BaseFragment;

import butterknife.BindView;

/*
 * Created by dendy-prtha on 02/04/2019.
 * Fragment for Movie List
 */

public class FragmentMovieList extends BaseFragment {


    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    public static FragmentMovieList newInstance() {

        final FragmentMovieList mf = new FragmentMovieList();
        mf.setFragmentTitle("Movie");
        return mf;
    }


    @Override
    protected int getLayout() {
        return R.layout.fragment_movies;
    }

    @Override
    protected void init() {
        //dummy list
        String[] items = getResources().getStringArray(R.array.tab_A);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(items);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
