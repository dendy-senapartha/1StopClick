package com.domain.track.repository;

import com.domain.track.SongResult;
import com.domain.track.TrackResult;
import com.domain.track.interactor.FindTrackByProductId;
import com.domain.track.interactor.FindUserBuyedSongsByAlbumId;
import com.domain.track.interactor.GetAlbumSong;

import java.util.List;

import io.reactivex.Observable;

/*
 * Created by dendy-prtha on 11/06/2019.
 * track Repository interface
 */

public interface TrackRepository {

    Observable<List<TrackResult>> findTrackByProductId(FindTrackByProductId.Params params);

    Observable<List<SongResult>> getAlbumSongs(GetAlbumSong.Params params);

    Observable<List<SongResult>> findUserBuyedSongsByAlbumId(FindUserBuyedSongsByAlbumId.Params params);
}
