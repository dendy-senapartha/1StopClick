package com.data.album.mapper;

import com.data.album.AlbumEntity;
import com.data.album.repository.source.network.response.AlbumListResponse;
import com.data.artist.ArtistEntity;
import com.data.productimage.ProductImageEntity;
import com.data.track.TrackEntity;
import com.domain.base.entity.Album;
import com.domain.base.entity.Artist;
import com.domain.base.entity.Category;
import com.domain.base.entity.Product;
import com.domain.base.entity.ProductImage;
import com.domain.base.entity.ProductImageType;
import com.domain.base.entity.Subcategory;
import com.domain.base.entity.Track;
import com.domain.base.result.AlbumResult;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/*
 * Created by dendy-prtha on 18/04/2019.
 * Album list response mapper
 */

@Singleton
public class AlbumListRespondMapper {

    @Inject
    public AlbumListRespondMapper() {

    }

    public List<AlbumResult> transform(AlbumListResponse response) {
        List<AlbumResult> result = new ArrayList<>();
        if (response != null && !response.albumEntityList.isEmpty()) {
            for (AlbumEntity albumEntity : response.albumEntityList) {
                AlbumResult albumResult = new AlbumResult();
                albumResult.album = new Album();
                albumResult.album.id = albumEntity.id;
                albumResult.album.name = albumEntity.name;
                albumResult.album.releaseDate = albumEntity.releaseDate;
                albumResult.album.albumImageUrl = albumEntity.albumImageUrl;

                result.add(albumResult);
            }
        }
        return result;
    }
}
