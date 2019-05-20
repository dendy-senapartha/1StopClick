package com.data.product;

import com.data.category.CategoryEntity;
import com.data.productimage.ProductImageEntity;
import com.data.subcategory.SubcategoryEntity;
import com.data.video.VideoEntity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/*
 * Created by dendy-prtha on 16/04/2019.
 * Product Entity
 */

public class ProductEntity {

    public int id;

    public String productName;

    public BigDecimal packageCode;

    public BigDecimal price;

    public String description;

    public String compatibility;

    public String status;

    public Date created;

    public CategoryEntity category;

    public SubcategoryEntity subcategory;

    public List<ProductImageEntity> productImageList;

    public List<VideoEntity> videoList;
}
