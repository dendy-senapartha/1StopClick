package com.a1stopclick.homeactivity.movielist.moviedetails;
import com.a1stopclick.base.BaseContract;

/*
 * Created by dendy-prtha on 10/05/2019.
 * movie Detail Contract
 */

public interface MovieDetailContract {
    interface View extends BaseContract.BaseViewContract {

    }

    interface Presenter extends BaseContract.BasePresenterContract{
        void initPresenter();
    }
}
