package com.domain.music.repository;

import com.domain.base.ProductResult;
import com.domain.music.MusicListResult;
import com.domain.music.interactor.GetAllMusic;

import java.util.List;

import io.reactivex.Observable;

/*
 * Created by dendy-prtha on 26/04/2019.
 * Music Repository contract
 */

public interface MusicRepository {

    Observable<List<ProductResult>> getAllMusic(GetAllMusic.Params params);
}
