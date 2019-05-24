package com.data.product.mapper;

import com.data.product.ProductEntity;
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
import com.domain.base.result.ProductResult;
import com.domain.product.MovieListResult;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/*
 * Created by dendy-prtha on 18/04/2019.
 * Movie list response mapper
 */

@Singleton
public class ProductListRespondMapper {

    @Inject
    public ProductListRespondMapper() {

    }

    public List<ProductResult> transform(ProductListResponse response) {
        List<ProductResult> result = new ArrayList<>();
        if (response != null && !response.productEntityList.isEmpty()) {
            for (ProductEntity productEntity : response.productEntityList) {
                ProductResult movieListResult = new ProductResult();
                movieListResult.product = new Product();
                movieListResult.product.id = productEntity.id;
                movieListResult.product.productName = productEntity.productName;
                movieListResult.product.packageCode = productEntity.packageCode;
                movieListResult.product.price = productEntity.price;
                movieListResult.product.description = productEntity.description;
                movieListResult.product.compatibility = productEntity.compatibility;
                movieListResult.product.status = productEntity.status;
                movieListResult.product.created = productEntity.created;

                //product image
                movieListResult.product.productImageList = new ArrayList<>();
                for (int imageCount = 0; imageCount < productEntity.productImageList.size(); imageCount++) {
                    ProductImage productImage = new ProductImage();
                    productImage.id = productEntity.productImageList.get(imageCount).id;
                    productImage.imageUrl = productEntity.productImageList.get(imageCount).imageUrl;
                    productImage.productImageType = new ProductImageType();
                    if (productEntity.productImageList.get(imageCount).productImageType != null) {
                        productImage.productImageType.id = productEntity.productImageList.get(imageCount).productImageType.id;
                        productImage.productImageType.code = productEntity.productImageList.get(imageCount).productImageType.code;
                        productImage.productImageType.name = productEntity.productImageList.get(imageCount).productImageType.name;
                    }
                    productImage.product = new Product();

                    movieListResult.product.productImageList.add(productImage);
                }

                //video
                movieListResult.product.videoList = new ArrayList<>();
                for (int videoCount = 0; videoCount < productEntity.videoList.size(); videoCount++) {
                    Video video = new Video();
                    video.id = productEntity.videoList.get(videoCount).id;
                    video.releaseDate = productEntity.videoList.get(videoCount).releaseDate;
                    video.studio = productEntity.videoList.get(videoCount).studio;
                    video.ageRating = productEntity.videoList.get(videoCount).ageRating;
                    video.avgRating = productEntity.videoList.get(videoCount).avgRating;
                    video.overallRank = productEntity.videoList.get(videoCount).overallRank;
                    video.streamUrl = productEntity.videoList.get(videoCount).streamUrl;
                    video.duration = productEntity.videoList.get(videoCount).duration;
                    video.videoType = new Video.VideoType();
                    if (productEntity.videoList.get(videoCount).videoType != null) {
                        video.videoType.id = productEntity.videoList.get(videoCount).videoType.id;
                        video.videoType.code = productEntity.videoList.get(videoCount).videoType.code;
                        video.videoType.name = productEntity.videoList.get(videoCount).videoType.name;
                    }

                    video.actors = new ArrayList<>();
                    for (int actorCount = 0;
                         actorCount < productEntity.videoList.get(videoCount).actors.size();
                         actorCount++) {
                        Actor actor = new Actor();
                        actor.id = productEntity.videoList.get(videoCount).actors.get(actorCount).id;
                        actor.firstName = productEntity.videoList.get(videoCount).actors.get(actorCount).firstName;
                        actor.lastName = productEntity.videoList.get(videoCount).actors.get(actorCount).lastName;
                        video.actors.add(actor);
                    }

                    video.directors = new ArrayList<>();
                    for (int directorCount = 0;
                         directorCount < productEntity.videoList.get(videoCount).directors.size();
                         directorCount++) {
                        Director director = new Director();
                        director.id = productEntity.videoList.get(videoCount).directors.get(directorCount).id;
                        director.firstName = productEntity.videoList.get(videoCount).directors.get(directorCount).firstName;
                        director.lastName = productEntity.videoList.get(videoCount).directors.get(directorCount).lastName;
                        video.directors.add(director);
                    }
                    video.product = new Product();
                    movieListResult.product.videoList.add(video);
                }

                //track
                movieListResult.product.trackList = new ArrayList<>();
                for (int trackCount = 0; trackCount < productEntity.trackList.size(); trackCount++) {
                    Track track = new Track();
                    track.id = productEntity.trackList.get(trackCount).id;

                    track.streamUrl = productEntity.trackList.get(trackCount).streamUrl;
                    track.length = productEntity.trackList.get(trackCount).length;
                    track.trackType = new Track.TrackType();
                    if (productEntity.trackList.get(trackCount).trackType != null) {
                        track.trackType.id = productEntity.trackList.get(trackCount).trackType.id;
                        track.trackType.code = productEntity.trackList.get(trackCount).trackType.code;
                        track.trackType.name = productEntity.trackList.get(trackCount).trackType.name;
                    }

                    track.artists = new ArrayList<>();
                    for (int artistCount = 0;
                         artistCount < productEntity.trackList.get(trackCount).artists.size();
                         artistCount++) {
                        Artist artist = new Artist();
                        artist.id = productEntity.trackList.get(trackCount).artists.get(artistCount).id;
                        artist.firstName = productEntity.trackList.get(trackCount).artists.get(artistCount).firstName;
                        artist.lastName = productEntity.trackList.get(trackCount).artists.get(artistCount).lastName;
                        track.artists.add(artist);
                    }

                    track.albums = new ArrayList<>();
                    for (int albumsCount = 0;
                         albumsCount < productEntity.trackList.get(trackCount).albums.size();
                         albumsCount++) {
                        Album album = new Album();
                        album.id = productEntity.trackList.get(trackCount).albums.get(albumsCount).id;
                        album.name = productEntity.trackList.get(trackCount).albums.get(albumsCount).name;
                        album.releaseDate = productEntity.trackList.get(trackCount).albums.get(albumsCount).releaseDate;
                        track.albums.add(album);
                    }
                    track.product = new Product();
                    movieListResult.product.trackList.add(track);
                }

                movieListResult.product.category = new Category();
                movieListResult.product.category.id = productEntity.category.id;
                movieListResult.product.category.name = productEntity.category.name;
                movieListResult.product.category.isActive = productEntity.category.isActive;
                movieListResult.product.category.created = productEntity.category.created;
                movieListResult.product.category.target = productEntity.category.target;
                movieListResult.product.category.priority = productEntity.category.priority;

                movieListResult.product.subcategory = new Subcategory();
                movieListResult.product.subcategory.id = productEntity.subcategory.id;
                movieListResult.product.subcategory.name = productEntity.subcategory.name;
                movieListResult.product.subcategory.created = productEntity.subcategory.created;
                movieListResult.product.subcategory.isActive = productEntity.subcategory.isActive;
                movieListResult.product.subcategory.target = productEntity.subcategory.target;
                movieListResult.product.subcategory.priority = productEntity.subcategory.priority;

                result.add(movieListResult);
            }
            //result = response.productEntityList;
        }
        return result;
    }
}
