package com.data.music.mapper;

import com.data.music.MusicEntity;
import com.data.music.repository.source.network.response.MusicListResponse;
import com.domain.base.ProductResult;
import com.domain.music.MusicListResult;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/*
 * Created by dendy-prtha on 26/04/2019.
 * mapper between music list response and result to view
 */

@Singleton
public class MusicListRespondMapper {
    @Inject
    public MusicListRespondMapper() {

    }

    public List<ProductResult> transform(MusicListResponse response) {
        List<ProductResult> result = new ArrayList<>();
        if (response != null && !response.musicList.isEmpty()) {
            for(MusicEntity musicEntity : response.musicList)
            {
                MusicListResult musicListResult = new MusicListResult();
                musicListResult.id = musicEntity.id;
                musicListResult.productName = musicEntity.productName;
                musicListResult.packageCode = musicEntity.packageCode;
                musicListResult.price = musicEntity.price;
                musicListResult.description = musicEntity.description;
                musicListResult.compatibility = musicEntity.compatibility;
                musicListResult.urldownload = musicEntity.urldownload;
                musicListResult.status = musicEntity.status;
                musicListResult.created = musicEntity.created;
                musicListResult.productArt= musicEntity.productArt;
                //movieListResult.category = movieEntity.category;
                //movieListResult.subcategory = movieEntity.subcategory;
                result.add(musicListResult);
            }
            //result = response.movieList;
        }
        return result;
    }
}
