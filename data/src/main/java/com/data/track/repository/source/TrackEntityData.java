package com.data.track.repository.source;

import com.data.track.repository.source.network.request.FindTrackByProductIdRequest;
import com.data.track.repository.source.network.request.FindUserBuyedSongsByAlbumIdRequest;
import com.data.track.repository.source.network.request.GetAlbumSongRequest;
import com.data.track.repository.source.network.response.FindUserBuyedSongsByAlbumIdResponse;
import com.data.track.repository.source.network.response.GetAlbumSongsResponse;
import com.data.track.repository.source.network.response.TrackListResponse;
import com.domain.track.interactor.GetAlbumSong;

import io.reactivex.Observable;

/*
 * Created by dendy-prtha on 11/06/2019.
 * Video entity data
 */

public interface TrackEntityData {

    Observable<TrackListResponse> findTrackByProductId(FindTrackByProductIdRequest request);
    Observable<GetAlbumSongsResponse> getAlbumSongs(GetAlbumSongRequest request);
    Observable<FindUserBuyedSongsByAlbumIdResponse> findUserBuyedSongsByAlbumId(FindUserBuyedSongsByAlbumIdRequest request);
}
