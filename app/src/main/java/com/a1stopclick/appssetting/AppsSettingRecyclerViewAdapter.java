package com.a1stopclick.appssetting;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.a1stopclick.R;
import com.a1stopclick.home.movielist.MovieItemViewHolder;

import java.util.List;

/**
 * Created by dendy.prtha
 */

public class AppsSettingRecyclerViewAdapter extends RecyclerView.Adapter<AppsSettingItemViewHolder> {

    List<SettingItem> items;
    FragmentAppsSetting parentFragment;

    public AppsSettingRecyclerViewAdapter(List<SettingItem> items, FragmentAppsSetting fragment) {
        this.items = items;
        this.parentFragment = fragment;
    }

    @Override
    public AppsSettingItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.apps_setting_item, parent, false);
        return new AppsSettingItemViewHolder(view, parentFragment);
    }

    @Override
    public void onBindViewHolder(AppsSettingItemViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
