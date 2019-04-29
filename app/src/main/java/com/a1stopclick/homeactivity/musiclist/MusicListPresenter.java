package com.a1stopclick.homeactivity.musiclist;

import android.content.Context;
import android.util.Log;

import com.domain.DefaultObserver;
import com.domain.account.AccountResult;
import com.domain.account.interactor.GetAccount;
import com.domain.base.ProductResult;
import com.domain.music.interactor.GetAllMusic;

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
    private final GetAllMusic getAllMusic;
    private final GetAccount getAccount;
    private AccountResult userSession = null;

    @Inject
    public MusicListPresenter(MusicListContract.View view, Context context, GetAllMusic getAllMusic, GetAccount getAccount) {
        this.view = view;
        this.context = context;
        this.getAllMusic = getAllMusic;
        this.getAccount = getAccount;
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
        getAllMusic.execute(new DefaultObserver<List<ProductResult>>() {
                                @Override
                                public void onNext(List<ProductResult> result) {
                                    view.setLoadingIndicator(false);
                                    view.onMusicListSuccess(result);
                                }

                                @Override
                                public void onError(Throwable er) {
                                    //TODO : need show error message based on error code from BE
                                    view.setLoadingIndicator(false);
                                }
                            },
                GetAllMusic.Params.forGetMusicList(userSession.getAuthorization())
        );
    }

    @Override
    public void onDestroy() {

    }
}
