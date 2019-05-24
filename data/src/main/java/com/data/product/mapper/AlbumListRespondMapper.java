package com.data.product.mapper;

import com.data.album.AlbumEntity;
import com.data.product.ProductEntity;
import com.data.product.repository.source.network.response.AlbumListResponse;
import com.data.product.repository.source.network.response.ProductListResponse;
import com.domain.base.entity.Actor;
import com.domain.base.entity.Album;
import com.domain.base.entity.Artist;
import com.domain.base.entity.Category;
import com.domain.base.entity.Director;
import com.domain.base.entity.Product;
import com.domain.base.entity.ProductImage;
import com.domain.base.entity.ProductImageType;
import com.domain.base.entity.Subcategory;
import com.domain.base.entity.Track;
import com.domain.base.entity.Video;
import com.domain.base.result.AlbumResult;
import com.domain.base.result.ProductResult;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/*
 * Created by dendy-prtha on 18/04/2019.
 * Movie list response mapper
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

                albumResult.album.tracks = new ArrayList<>();
                for (int trackCount = 0; trackCount < albumEntity.tracks.size(); trackCount++) {
                    Track track = new Track();
                    track.id = albumEntity.tracks.get(trackCount).id;
                    track.streamUrl = albumEntity.tracks.get(trackCount).streamUrl;
                    track.length = albumEntity.tracks.get(trackCount).length;

                    track.trackType = new Track.TrackType();
                    if (albumEntity.tracks.get(trackCount).trackType != null) {
                        track.trackType.id = albumEntity.tracks.get(trackCount).trackType.id;
                        track.trackType.code = albumEntity.tracks.get(trackCount).trackType.code;
                        track.trackType.name = albumEntity.tracks.get(trackCount).trackType.name;
                    }

                    track.albums = new ArrayList<>();

                    track.artists = new ArrayList<>();
                    for (int artistCount = 0;
                         artistCount < albumEntity.tracks.get(trackCount).artists.size();
                         artistCount++) {
                        Artist artist = new Artist();
                        artist.id = albumEntity.tracks.get(trackCount).artists.get(artistCount).id;
                        artist.firstName = albumEntity.tracks.get(trackCount).artists.get(artistCount).firstName;
                        artist.lastName = albumEntity.tracks.get(trackCount).artists.get(artistCount).lastName;
                        track.artists.add(artist);
                    }

                    track.product = new Product();
                    track.product.id = albumEntity.tracks.get(trackCount).product.id;
                    track.product.productName = albumEntity.tracks.get(trackCount).product.productName;
                    track.product.packageCode = albumEntity.tracks.get(trackCount).product.packageCode;
                    track.product.price = albumEntity.tracks.get(trackCount).product.price;
                    track.product.description = albumEntity.tracks.get(trackCount).product.description;
                    track.product.compatibility = albumEntity.tracks.get(trackCount).product.compatibility;
                    track.product.status = albumEntity.tracks.get(trackCount).product.status;
                    track.product.created = albumEntity.tracks.get(trackCount).product.created;

                    //product image
                    track.product.productImageList = new ArrayList<>();
                    for (int imageCount = 0; imageCount < albumEntity.tracks.get(trackCount).product.productImageList.size();
                         imageCount++) {
                        ProductImage productImage = new ProductImage();
                        productImage.id = albumEntity.tracks.get(trackCount).product.productImageList.get(imageCount).id;
                        productImage.imageUrl = albumEntity.tracks.get(trackCount).product.productImageList.get(imageCount).imageUrl;
                        productImage.productImageType = new ProductImageType();
                        if (albumEntity.tracks.get(trackCount).product.productImageList.get(imageCount).productImageType != null) {
                            productImage.productImageType.id = albumEntity.tracks.get(trackCount).product.productImageList.get(imageCount).productImageType.id;
                            productImage.productImageType.code = albumEntity.tracks.get(trackCount).product.productImageList.get(imageCount).productImageType.code;
                            productImage.productImageType.name = albumEntity.tracks.get(trackCount).product.productImageList.get(imageCount).productImageType.name;
                        }
                        productImage.product = new Product();

                        track.product.productImageList.add(productImage);
                    }

                    track.product.category = new Category();
                    track.product.category.id = albumEntity.tracks.get(trackCount).product.category.id;
                    track.product.category.name = albumEntity.tracks.get(trackCount).product.category.name;
                    track.product.category.created = albumEntity.tracks.get(trackCount).product.category.created;
                    track.product.category.isActive = albumEntity.tracks.get(trackCount).product.category.isActive;
                    track.product.category.priority = albumEntity.tracks.get(trackCount).product.category.priority;
                    track.product.category.target = albumEntity.tracks.get(trackCount).product.category.target;

                    track.product.subcategory = new Subcategory();
                    track.product.subcategory.id = albumEntity.tracks.get(trackCount).product.subcategory.id;
                    track.product.subcategory.name = albumEntity.tracks.get(trackCount).product.subcategory.name;
                    track.product.subcategory.created = albumEntity.tracks.get(trackCount).product.subcategory.created;
                    track.product.subcategory.isActive = albumEntity.tracks.get(trackCount).product.subcategory.isActive;
                    track.product.subcategory.priority = albumEntity.tracks.get(trackCount).product.subcategory.priority;
                    track.product.subcategory.target = albumEntity.tracks.get(trackCount).product.subcategory.target;


                    albumResult.album.tracks.add(track);
                }

                result.add(albumResult);
            }
        }

        //result = response.productEntityList;

        return result;
    }
}
