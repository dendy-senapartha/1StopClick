package com.a1stopclick.albumdetails;

import android.content.Context;

import com.a1stopclick.OneStopClickApplication;
import com.domain.DefaultObserver;
import com.domain.account.AccountResult;
import com.domain.order.AddItemToOrderResult;
import com.domain.order.interactor.AddItemToOrder;
import com.domain.track.SongResult;
import com.domain.track.interactor.FindUserBuyedSongsByAlbumId;
import com.domain.track.interactor.GetAlbumSong;

import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

/*
 * Created by dendy-prtha on 10/05/2019.
 * Movie Detail Presenter
 */

public class AlbumDetailPresenter implements AlbumDetailContract.Presenter {

    private final String TAG = AlbumDetailPresenter.class.getSimpleName();

    private final Context context;
    private final AlbumDetailContract.View view;
    private final GetAlbumSong getAlbumSong;
    private final FindUserBuyedSongsByAlbumId findUserBuyedSongsByAlbumId;
    private final AddItemToOrder addItemToOrder;
    private AccountResult userSession = null;


    @Inject
    public AlbumDetailPresenter(Context context, AlbumDetailContract.View view, GetAlbumSong getAlbumSong,
                                FindUserBuyedSongsByAlbumId findUserBuyedSongsByAlbumId, AddItemToOrder addItemToOrder) {
        this.context = context;
        this.view = view;
        this.getAlbumSong = getAlbumSong;
        this.findUserBuyedSongsByAlbumId = findUserBuyedSongsByAlbumId;
        this.addItemToOrder = addItemToOrder;
    }

    @Override
    public void initPresenter() {
        userSession = ((OneStopClickApplication) context).getSession();
    }

    private boolean songAlreadyBought(List<SongResult> userBuyedSongs, List<SongResult> albumSongs) {
        boolean isBought = false;
        for (SongResult songOfTheAlbum : albumSongs) {
            for (SongResult userBuyedSong : userBuyedSongs) {
                if (songOfTheAlbum.song.id == userBuyedSong.song.id) {
                    isBought = true;
                }
            }
        }
        return isBought;
    }

    @Override
    public void getAlbumSongs(String albumId) {
        getAlbumSong.execute(new DefaultObserver<List<SongResult>>() {
                                 @Override
                                 public void onNext(List<SongResult> albumSongs) {
                                     view.setAlbumDetailGenre(albumSongs);

                                     findUserBuyedSongsByAlbumId.execute(
                                             new DefaultObserver<List<SongResult>>() {
                                                 @Override
                                                 public void onNext(List<SongResult> userBuyedSongs) {
                                                     //check if songs already bought
                                                     for (Iterator<SongResult> songOfTheAlbumIterator = albumSongs.iterator();
                                                          songOfTheAlbumIterator.hasNext(); ) {
                                                         SongResult songOfTheAlbum = songOfTheAlbumIterator.next();
                                                         for (Iterator<SongResult> userBuyedSongIterator = userBuyedSongs.iterator();
                                                              userBuyedSongIterator.hasNext(); ) {
                                                             SongResult userBuyedSong = userBuyedSongIterator.next();
                                                             if (songOfTheAlbum.song.id == userBuyedSong.song.id) {
                                                                 songOfTheAlbumIterator.remove();
                                                             }
                                                         }
                                                     }
                                                     view.onGetAlbumSongsSuccess(albumSongs);
                                                 }

                                                 @Override
                                                 public void onError(Throwable er) {
                                                     //TODO : need show error message based on error code from BE
                                                     view.onError(er.getMessage());
                                                 }
                                             },
                                             FindUserBuyedSongsByAlbumId.Params.forFindUserBuyedSongsByAlbumId(userSession.getAuthorization(),
                                                     userSession.getUid(), albumId)
                                     );
                                 }

                                 @Override
                                 public void onError(Throwable er) {
                                     //TODO : need show error message based on error code from BE
                                     view.onError(er.getMessage());
                                 }
                             },
                GetAlbumSong.Params.forGetAlbumSong(userSession.getAuthorization(), albumId)
        );
    }

    @Override
    public void getUserBuyedAlbumSongs(String userId, String albumId) {
        findUserBuyedSongsByAlbumId.execute(new DefaultObserver<List<SongResult>>() {
                                                @Override
                                                public void onNext(List<SongResult> result) {
                                                    view.onGetUserBuyedAlbumSongs(result);
                                                }

                                                @Override
                                                public void onError(Throwable er) {
                                                    //TODO : need show error message based on error code from BE
                                                    view.onError(er.getMessage());
                                                }
                                            },
                FindUserBuyedSongsByAlbumId.Params.forFindUserBuyedSongsByAlbumId(userSession.getAuthorization(), userId, albumId)
        );
    }

    @Override
    public void addSongToOrder(int productId, int quantity) {
        addItemToOrder.execute(new DefaultObserver<AddItemToOrderResult>() {
                                   @Override
                                   public void onNext(AddItemToOrderResult result) {
                                       view.onAddSongToOrderSuccess(result);
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
