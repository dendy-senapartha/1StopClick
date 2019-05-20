package com.data.product.mapper;

import com.data.product.ProductEntity;
import com.data.product.repository.source.network.response.ProductListResponse;
import com.domain.base.entity.Actor;
import com.domain.base.entity.Category;
import com.domain.base.entity.Director;
import com.domain.base.entity.Product;
import com.domain.base.entity.ProductImage;
import com.domain.base.entity.ProductImageType;
import com.domain.base.entity.Subcategory;
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
        if (response != null && !response.movieList.isEmpty()) {
            for (ProductEntity movieEntity : response.movieList) {
                MovieListResult movieListResult = new MovieListResult();
                movieListResult.product = new Product();
                movieListResult.product.id = movieEntity.id;
                movieListResult.product.productName = movieEntity.productName;
                movieListResult.product.packageCode = movieEntity.packageCode;
                movieListResult.product.price = movieEntity.price;
                movieListResult.product.description = movieEntity.description;
                movieListResult.product.compatibility = movieEntity.compatibility;
                movieListResult.product.status = movieEntity.status;
                movieListResult.product.created = movieEntity.created;

                movieListResult.product.productImageList = new ArrayList<>();
                for (int imageCount = 0; imageCount < movieEntity.productImageList.size(); imageCount++) {
                    ProductImage productImage = new ProductImage();
                    productImage.id = movieEntity.productImageList.get(imageCount).id;
                    productImage.imageUrl = movieEntity.productImageList.get(imageCount).imageUrl;
                    productImage.productImageType = new ProductImageType();
                    if (movieEntity.productImageList.get(imageCount).productImageType != null) {
                        productImage.productImageType.id = movieEntity.productImageList.get(imageCount).productImageType.id;
                        productImage.productImageType.code = movieEntity.productImageList.get(imageCount).productImageType.code;
                        productImage.productImageType.name = movieEntity.productImageList.get(imageCount).productImageType.name;
                    }
                    productImage.product = new Product();

                    movieListResult.product.productImageList.add(productImage);
                }

                movieListResult.product.videoList = new ArrayList<>();
                for (int videoCount = 0; videoCount < movieEntity.videoList.size(); videoCount++) {
                    Video video = new Video();
                    video.id = movieEntity.videoList.get(videoCount).id;
                    video.releaseDate = movieEntity.videoList.get(videoCount).releaseDate;
                    video.studio = movieEntity.videoList.get(videoCount).studio;
                    video.ageRating = movieEntity.videoList.get(videoCount).ageRating;
                    video.avgRating = movieEntity.videoList.get(videoCount).avgRating;
                    video.overallRank = movieEntity.videoList.get(videoCount).overallRank;
                    video.streamUrl = movieEntity.videoList.get(videoCount).streamUrl;
                    video.duration = movieEntity.videoList.get(videoCount).duration;
                    video.videoType = new Video.VideoType();
                    if (movieEntity.videoList.get(videoCount).videoType != null) {
                        video.videoType.id = movieEntity.videoList.get(videoCount).videoType.id;
                        video.videoType.code = movieEntity.videoList.get(videoCount).videoType.code;
                        video.videoType.name = movieEntity.videoList.get(videoCount).videoType.name;
                    }

                    video.actors = new ArrayList<>();
                    for (int actorCount = 0;
                         actorCount < movieEntity.videoList.get(videoCount).actors.size();
                         actorCount++) {
                        Actor actor = new Actor();
                        actor.id = movieEntity.videoList.get(videoCount).actors.get(actorCount).id;
                        actor.firstName = movieEntity.videoList.get(videoCount).actors.get(actorCount).firstName;
                        actor.lastName = movieEntity.videoList.get(videoCount).actors.get(actorCount).lastName;
                        video.actors.add(actor);
                    }

                    video.directors = new ArrayList<>();
                    for (int directorCount = 0;
                         directorCount < movieEntity.videoList.get(videoCount).directors.size();
                         directorCount++) {
                        Director director = new Director();
                        director.id = movieEntity.videoList.get(videoCount).directors.get(directorCount).id;
                        director.firstName = movieEntity.videoList.get(videoCount).directors.get(directorCount).firstName;
                        director.lastName = movieEntity.videoList.get(videoCount).directors.get(directorCount).lastName;
                        video.directors.add(director);
                    }
                    video.product = new Product();
                    movieListResult.product.videoList.add(video);
                }

                movieListResult.product.category = new Category();
                movieListResult.product.category.id = movieEntity.category.id;
                movieListResult.product.category.name = movieEntity.category.name;
                movieListResult.product.category.isActive = movieEntity.category.isActive;
                movieListResult.product.category.created = movieEntity.category.created;
                movieListResult.product.category.target = movieEntity.category.target;
                movieListResult.product.category.priority = movieEntity.category.priority;

                movieListResult.product.subcategory = new Subcategory();
                movieListResult.product.subcategory.id = movieEntity.subcategory.id;
                movieListResult.product.subcategory.name = movieEntity.subcategory.name;
                movieListResult.product.subcategory.created = movieEntity.subcategory.created;
                movieListResult.product.subcategory.isActive = movieEntity.subcategory.isActive;
                movieListResult.product.subcategory.target = movieEntity.subcategory.target;
                movieListResult.product.subcategory.priority = movieEntity.subcategory.priority;

                result.add(movieListResult);
            }
            //result = response.movieList;
        }
        return result;
    }
}
