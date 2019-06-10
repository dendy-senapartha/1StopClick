package com.a1stopclick.homeactivity.musiclist;

import android.content.Context;
import android.util.Log;

import com.domain.DefaultObserver;
import com.domain.account.AccountResult;
import com.domain.account.interactor.GetAccount;
import com.domain.base.result.AlbumResult;
import com.domain.base.result.ProductResult;
import com.domain.product.interactor.FindTrackByTitle;
import com.domain.product.interactor.GetAllAlbum;

import java.util.List;

import javax.inject.Inject;

/*
 * Created by dendy-prtha on 26/04/2019.
 * Music List Presenter
 */

public class MusicListPresenter implements MusicListContract.Presenter {

    private final String TAG = MusicListPresenter.class.getSimpleName();

    private final MusicListContract.View view;
    private final Context context;
    private final FindTrackByTitle findTrackByTitle;
    private final GetAccount getAccount;

    private final GetAllAlbum getAllAlbum;
    private AccountResult userSession = null;

    @Inject
    public MusicListPresenter(MusicListContract.View view, Context context,
                              FindTrackByTitle findTrackByTitle, GetAccount getAccount, GetAllAlbum getAllAlbum) {
        this.view = view;
        this.context = context;
        this.findTrackByTitle = findTrackByTitle;
        this.getAccount = getAccount;
        this.getAllAlbum = getAllAlbum;
    }

    @Override
    public void initPresenter() {
        retrieveSession();
    }

    private void retrieveSession() {
        getAccount.execute(new DefaultObserver<AccountResult>() {
            @Override
            public void onNext(AccountResult result) {
                userSession = result;
                getMusicList();
            }

            @Override
            public void onError(Throwable er) {
                //TODO : need show error message based on error code from BE
                Log.d(TAG, "onError: " + er.toString());

            }
        });
    }

    @Override
    public AccountResult getSession() {
        return null;
    }

    @Override
    public void getMusicList() {
        view.setLoadingIndicator(true);
        getAllAlbum.execute(new DefaultObserver<List<AlbumResult>>() {
                                @Override
                                public void onNext(List<AlbumResult> result) {
                                    view.setLoadingIndicator(false);
                                    view.onAlbumListSuccess(result);
                                }

                                @Override
                                public void onError(Throwable er) {
                                    //TODO : need show error message based on error code from BE
                                    view.setLoadingIndicator(false);
                                }
                            },
                GetAllAlbum.Params.forGetAlbumList(userSession.getAuthorization())
        );
    }

    @Override
    public void findTrackByTitle(String searchTrackQuery) {
        view.setLoadingIndicator(true);
        findTrackByTitle.execute(new DefaultObserver<List<ProductResult>>() {
                                @Override
                                public void onNext(List<ProductResult> result) {
                                    view.setLoadingIndicator(false);
                                    view.onFindTrackSuccess(result);
                                }

                                @Override
                                public void onError(Throwable er) {
                                    //TODO : need show error message based on error code from BE
                                    view.setLoadingIndicator(false);
                                }
                            },
                FindTrackByTitle.Params.forFindTrackByTitle(userSession.getAuthorization(), searchTrackQuery)
        );
    }

    @Override
    public void onDestroy() {

    }
}
