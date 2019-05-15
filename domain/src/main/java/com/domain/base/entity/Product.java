package com.domain.base.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/*
 * Created by dendy-prtha on 16/04/2019.
 * Movie List Entity
 */

public class Product {

    public int id;

    public String productName;

    public BigDecimal packageCode;

    public BigDecimal price;

    public String description;

    public String compatibility;

    public String urldownload;

    public String status;

    public Date created;

    public Category category;

    public Subcategory subcategory;

    public String youtubeTrailerId;

    public List<ProductImage> productImageList;
}
