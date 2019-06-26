package com.a1stopclick.appssetting;

import com.a1stopclick.base.BaseContract;
import com.domain.product.ProductResult;

import java.util.List;

/*
 * Created by dendy-prtha on 16/04/2019.
 * productEntityList contract
 */

public interface AppsSettingContract {
    interface View
    {
        void setLoadingIndicator(boolean active);
        void onSuccessLogout();
    }

    interface Presenter extends BaseContract.BasePresenterContract
    {
        void initPresenter();
        void logOut();
    }
}
