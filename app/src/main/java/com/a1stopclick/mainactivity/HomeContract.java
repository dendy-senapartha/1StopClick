package com.a1stopclick.mainactivity;

import com.a1stopclick.base.BaseContract.BasePresenterContract;

/*
 * Created by dendy-prtha on 14/03/2019.
 * Contract between view and presenter
 */

public interface HomeContract {
    interface View
    {
        void showLoading();
        void hideLoading();
    }

    interface Presenter extends BasePresenterContract
    {
    }
}
