package com.domain.video.repository;

import com.domain.video.VideoResult;
import com.domain.video.interactor.FindVideoByProductId;

import java.util.List;

import io.reactivex.Observable;

/*
 * Created by dendy-prtha on 11/06/2019.
 * Video Repository interface
 */

public interface VideoRepository {

    Observable<List<VideoResult>> findVideoByProductId(FindVideoByProductId.Params params);
}
