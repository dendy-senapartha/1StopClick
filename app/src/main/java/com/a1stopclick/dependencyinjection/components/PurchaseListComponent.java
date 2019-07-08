package com.a1stopclick.dependencyinjection.components;

import com.a1stopclick.dependencyinjection.PerActivity;
import com.a1stopclick.dependencyinjection.modules.MusicListModule;
import com.a1stopclick.dependencyinjection.modules.PurchaseListModule;
import com.a1stopclick.home.musiclist.FragmentMusicList;
import com.a1stopclick.transaction.purchase.FragmentPurchase;

import dagger.Component;

/*
 * Created by dendy-prtha on 26/04/2019.
 * Purchase List component for DI
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = PurchaseListModule.class)
public interface PurchaseListComponent {
    void inject(FragmentPurchase fragment);
}
