package com.a1stopclick.base;

/*
 * Created by dendy-prtha on 26/02/2019.
 * Base contract for MVP design pattern
 */
public interface BaseContract {

    interface BaseViewContract {
        void showProgress();

        void dismissProgress();

        void onError(String errorMsg);
    }

    interface BasePresenterContract {
        void onDestroy();
    }
}
