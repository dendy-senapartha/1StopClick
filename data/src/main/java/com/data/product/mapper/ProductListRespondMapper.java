package com.data.product.mapper;

import com.data.product.ProductEntity;
import com.data.product.repository.source.network.response.ProductListResponse;
import com.domain.base.entity.Category;
import com.domain.base.entity.Product;
import com.domain.base.entity.ProductImage;
import com.domain.base.entity.ProductImageType;
import com.domain.base.entity.Subcategory;
import com.domain.product.ProductResult;

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
