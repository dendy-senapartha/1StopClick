package com.data.productimage;

import com.data.product.ProductEntity;
import com.data.productimagetype.ProductImageTypeEntity;

/*
 * Created by dendy-prtha on 15/05/2019.
 * product image entity
 */

public class ProductImageEntity {

    public int id;

    public ProductEntity product;

    public ProductImageTypeEntity productImageType;

    public String imageUrl;
}
