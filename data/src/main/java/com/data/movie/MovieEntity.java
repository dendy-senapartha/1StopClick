package com.data.movie;

import java.math.BigDecimal;
import java.util.Date;

/*
 * Created by dendy-prtha on 16/04/2019.
 * Movie List Entity
 */

public class MovieEntity {

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

    public class Category {
        public int id;

        public String name;

        public int priority;

        public String target;

        public boolean isActive;

        public Date created;
    }

    public class Subcategory {
        public int id;

        public String name;

        public String target;

        public int priority;

        public  boolean isActive;

        public  Date created;

        //public  Category category;
    }
}
