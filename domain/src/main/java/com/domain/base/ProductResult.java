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

    public String productBackdrop;

    public String youtubeTrailerId;

    public static class Category {
        public int id;

        public String name;

        public String target;

        public int priority;

        public boolean isActive;

        public Date created;
    }

    public static class Subcategory {
        public int id;

        public String name;

        public String target;

        public int priority;

        public boolean isActive;

        public Date created;

        public  Category category;
    }
}
