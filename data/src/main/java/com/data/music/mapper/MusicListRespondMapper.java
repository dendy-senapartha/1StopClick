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

                musicListResult.category = new ProductResult.Category();
                musicListResult.category.id = musicEntity.category.id;
                musicListResult.category.name = musicEntity.category.name;
                musicListResult.category.isActive = musicEntity.category.isActive;
                musicListResult.category.created = musicEntity.category.created;
                musicListResult.category.target = musicEntity.category.target;
                musicListResult.category.priority = musicEntity.category.priority;

                musicListResult.subcategory = new ProductResult.Subcategory();
                musicListResult.subcategory.id = musicEntity.subcategory.id;
                musicListResult.subcategory.name = musicEntity.subcategory.name;
                musicListResult.subcategory.created = musicEntity.subcategory.created;
                musicListResult.subcategory.isActive = musicEntity.subcategory.isActive;
                musicListResult.subcategory.target = musicEntity.subcategory.target;
                musicListResult.subcategory.priority = musicEntity.subcategory.priority;
                result.add(musicListResult);
            }
            //result = response.movieList;
        }
        return result;
    }
}
