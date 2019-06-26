package com.a1stopclick.appssetting;

import android.content.Intent;
import android.view.View;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.a1stopclick.R;
import com.a1stopclick.base.BaseFragment;
import com.a1stopclick.dependencyinjection.components.AppsSettingComponent;
import com.a1stopclick.dependencyinjection.components.DaggerAppsSettingComponent;
import com.a1stopclick.dependencyinjection.components.MovieLibraryComponent;
import com.a1stopclick.dependencyinjection.modules.AppsSettingModule;
import com.a1stopclick.login.LoginActivity;
import com.a1stopclick.util.AndroidUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/*
 * Created by dendy-prtha on 12/06/2019.
 * app setting fragment
 */

public class FragmentAppsSetting extends BaseFragment implements AppsSettingContract.View {

    private AppsSettingComponent component;

    public static final String LOGOUT = "Logout";
    public static final String PROFILE = "Profile";

    @BindView(R.id.recyclerViewAppsSetting)
    RecyclerView recyclerViewAppsSetting;

    AppsSettingRecyclerViewAdapter appsSettingRecyclerViewAdapter;

    List<SettingItem> settingItemList = new ArrayList<>();

    @Inject
    AppsSettingContract.Presenter presenter;

    @Override
    public int getLayout() {
        return R.layout.fragment_apps_setting;
    }

    @Override
    public void init() {
        initComponent();
        prepareSeetingItem();
        getBaseActivity().getSupportActionBar().setTitle("Apps Setting");
    }

    private void prepareSeetingItem() {
        settingItemList.add(new SettingItem(PROFILE, true));
        settingItemList.add(new SettingItem(LOGOUT, false));

        appsSettingRecyclerViewAdapter = new AppsSettingRecyclerViewAdapter(settingItemList, this);
        recyclerViewAppsSetting.setAdapter(appsSettingRecyclerViewAdapter);
        recyclerViewAppsSetting.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewAppsSetting.setItemAnimator(new DefaultItemAnimator());
        appsSettingRecyclerViewAdapter.notifyDataSetChanged();
    }

    private void initComponent() {
        if (component == null) {
            component = DaggerAppsSettingComponent.builder()
                    .applicationComponent(getApplicationComponent())
                    .appsSettingModule(new AppsSettingModule(this))
                    .build();
        }
        component.inject(this);

        registerPresenter(presenter);
    }

    @Override
    public void setLoadingIndicator(boolean active) {
        if (active) {

        } else {

        }
    }

    public AppsSettingContract.Presenter getPresenter() {
        return presenter;
    }

    @Override
    public void onSuccessLogout() {
        requireActivity().finishAffinity();
        Intent intent = new Intent(requireActivity(), LoginActivity.class);
        startActivity(intent);
    }
}