package com.a1stopclick.dependencyinjection.modules;

import com.a1stopclick.dependencyinjection.PerActivity;
import com.a1stopclick.transaction.invoice.InvoiceContract;
import com.a1stopclick.transaction.invoice.InvoicePresenter;
import com.a1stopclick.transaction.purchase.PurchaseContract;
import com.a1stopclick.transaction.purchase.PurchasePresenter;

import dagger.Module;
import dagger.Provides;
/*
 * Created by dendy-prtha on 18/04/2019.
 * purchase List DI Module
 */

@Module
public class InvoiceListModule {

    private InvoiceContract.View view;

    public InvoiceListModule(InvoiceContract.View view){
        this.view = view;
    }

    @Provides
    @PerActivity
    InvoiceContract.View provideView()
    {
        return view;
    }

    @Provides
    @PerActivity
    InvoiceContract.Presenter providePresenter(InvoicePresenter presenter)
    {
        return presenter;
    }
}
