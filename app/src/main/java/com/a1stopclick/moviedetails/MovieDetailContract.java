package com.a1stopclick.moviedetails;

import com.a1stopclick.base.BaseContract;
import com.domain.order.AddItemToOrderResult;
import com.domain.product.ProductResult;
import com.domain.video.VideoResult;

import java.util.List;

/*
 * Created by dendy-prtha on 10/05/2019.
 * movie Detail Contract
 */

public interface MovieDetailContract {
    interface View extends BaseContract.BaseViewContract {
        void onGetVideoByProductIdSuccess(List<VideoResult> videoResults);
        void onSuccessFindVideoByProductId(Boolean result);
        ProductResult getMovieDetail();
        void onAddMovieToOrderSuccess(AddItemToOrderResult result);
    }

    interface Presenter extends BaseContract.BasePresenterContract {
        void initPresenter();
        void findVideoByProductId(String productId);
        void checAlreadyBuyedkMovie(String productId);
        void addMovieToOrder(int productId, int quantity);
    }
}
