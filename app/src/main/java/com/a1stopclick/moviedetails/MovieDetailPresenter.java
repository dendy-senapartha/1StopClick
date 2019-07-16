package com.a1stopclick.moviedetails;

import android.content.Context;

import com.a1stopclick.OneStopClickApplication;
import com.domain.DefaultObserver;
import com.domain.account.AccountResult;
import com.domain.order.AddItemToOrderResult;
import com.domain.order.interactor.AddItemToOrder;
import com.domain.product.ProductResult;
import com.domain.product.interactor.FindUserBuyedMoviesByProductId;
import com.domain.video.VideoResult;
import com.domain.video.interactor.FindVideoByProductId;

import java.util.List;

import javax.inject.Inject;

/*
 * Created by dendy-prtha on 10/05/2019.
 * Movie Detail Presenter
 */

public class MovieDetailPresenter implements MovieDetailContract.Presenter {

    private final String TAG = MovieDetailPresenter.class.getSimpleName();

    private final Context context;
    private final MovieDetailContract.View view;
    private final FindVideoByProductId findVideoByProductId;
    private final FindUserBuyedMoviesByProductId findUserBuyedMoviesByProductId;
    private AccountResult userSession = null;
    private final AddItemToOrder addItemToOrder;

    @Inject
    public MovieDetailPresenter(Context context, MovieDetailContract.View view, FindVideoByProductId findVideoByProductId,
                                FindUserBuyedMoviesByProductId findUserBuyedMoviesByProductId, AddItemToOrder addItemToOrder) {
        this.context = context;
        this.view = view;
        this.findVideoByProductId = findVideoByProductId;
        this.findUserBuyedMoviesByProductId = findUserBuyedMoviesByProductId;
        this.addItemToOrder = addItemToOrder;
    }

    private void retrieveSession() {
        userSession = ((OneStopClickApplication) context).getSession();
    }

    @Override
    public void initPresenter() {
        retrieveSession();
    }

    @Override
    public void findVideoByProductId(String productId) {
        findVideoByProductId.execute(new DefaultObserver<List<VideoResult>>() {
                                         @Override
                                         public void onNext(List<VideoResult> result) {
                                             view.onGetVideoByProductIdSuccess(result);
                                         }

                                         @Override
                                         public void onError(Throwable er) {
                                             //TODO : need show error message based on error code from BE

                                         }
                                     },
                FindVideoByProductId.Params.forFindVideoByProductId(userSession.getAuthorization(), productId)
        );
    }

    @Override
    public void checAlreadyBuyedkMovie(String productId) {
        findUserBuyedMoviesByProductId.execute(new DefaultObserver<List<ProductResult>>() {
                                                   @Override
                                                   public void onNext(List<ProductResult> result) {
                                                        view.onSuccessFindVideoByProductId(result);
                                                   }

                                                   @Override
                                                   public void onError(Throwable er) {
                                                       //TODO : need show error message based on error code from BE

                                                   }
                                               },
                FindUserBuyedMoviesByProductId.Params.forUserGetBuyedMoviesByProdId(userSession.getAuthorization(),
                        userSession.getUid(), productId)
        );
    }

    @Override
    public void addMovieToOrder(int productId, int quantity) {
        addItemToOrder.execute(new DefaultObserver<AddItemToOrderResult>() {
                                   @Override
                                   public void onNext(AddItemToOrderResult result) {
                                       view.onAddMovieToOrderSuccess(result);
                                   }

                                   @Override
                                   public void onError(Throwable er) {
                                       //TODO : need show error message based on error code from BE
                                       view.onError(er.getMessage());
                                   }
                               },
                AddItemToOrder.Params.forAddItemToOrder(userSession.getAuthorization(), userSession.getUid(), productId, quantity)
        );
    }

    @Override
    public void onDestroy() {

    }
}
