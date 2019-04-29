package com.domain.base;

import java.math.BigDecimal;
import java.util.Date;

/*
 * Created by dendy-prtha on 16/04/2019.
 * Movie List Result
 */

public class ProductResult {

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

    public String productArt;

    public class Category {
        public int id;

        public String name;

        public String target;

        public boolean isActive;

        public Date created;
    }

    public class Subcategory {
        public int id;

        public String target;

        public boolean isActive;

        public Date created;

        public  Category category;
    }
}
