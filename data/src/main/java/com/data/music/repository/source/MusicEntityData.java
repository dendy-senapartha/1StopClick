package com.data.music.repository.source;

import com.data.music.repository.source.network.request.MusicListRequest;
import com.data.music.repository.source.network.response.MusicListResponse;

import io.reactivex.Observable;

/*
 * Created by dendy-prtha on 26/04/2019.
 * music entity data interface
 */

public interface MusicEntityData {

    Observable<MusicListResponse> getMovieList(MusicListRequest request);
}
