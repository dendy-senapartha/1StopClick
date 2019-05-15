package com.data.product.mapper;

import com.data.product.ProductEntity;
import com.data.product.repository.source.network.response.ProductListResponse;
import com.domain.base.entity.Category;
import com.domain.base.entity.Product;
import com.domain.base.entity.ProductImage;
import com.domain.base.entity.ProductImageType;
import com.domain.base.entity.Subcategory;
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
                movieListResult.product.urldownload = movieEntity.urldownload;
                movieListResult.product.status = movieEntity.status;
                movieListResult.product.created = movieEntity.created;
                movieListResult.product.youtubeTrailerId = movieEntity.youtubeTrailerId;

                movieListResult.product.productImageList = new ArrayList<>();
                for (int i = 0; i < movieEntity.productImageList.size(); i++) {
                    ProductImage productImage = new ProductImage();
                    productImage.id = movieEntity.productImageList.get(i).id;
                    productImage.imageUrl = movieEntity.productImageList.get(i).imageUrl;
                    productImage.productImageType = new ProductImageType();
                    if (movieEntity.productImageList.get(i).productImageType != null) {
                        productImage.productImageType.id = movieEntity.productImageList.get(i).productImageType.id;
                        productImage.productImageType.code = movieEntity.productImageList.get(i).productImageType.code;
                        productImage.productImageType.name = movieEntity.productImageList.get(i).productImageType.name;
                    }
                    productImage.product = new Product();

                    movieListResult.product.productImageList.add(productImage);
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
