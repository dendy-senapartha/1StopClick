package com.a1stopclick.dependencyinjection.components;

import com.a1stopclick.dependencyinjection.PerActivity;
import com.a1stopclick.dependencyinjection.modules.InvoiceListModule;
import com.a1stopclick.dependencyinjection.modules.PurchaseListModule;
import com.a1stopclick.transaction.invoice.FragmentInvoice;
import com.a1stopclick.transaction.purchase.FragmentPurchase;

import dagger.Component;

/*
 * Created by dendy-prtha on 26/04/2019.
 * Purchase List component for DI
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = InvoiceListModule.class)
public interface InvoiceListComponent {
    void inject(FragmentInvoice fragment);
}
