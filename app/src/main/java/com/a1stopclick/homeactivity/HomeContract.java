package com.a1stopclick.homeactivity;

import com.a1stopclick.base.BaseContract.BasePresenterContract;

/*
 * Created by dendy-prtha on 14/03/2019.
 * Contract between view and presenter
 */

public interface HomeContract {
    interface View
    {
        void onSuccessLogout();
    }

    interface Presenter extends BasePresenterContract
    {
        void logOut();
    }
}