package com.data.video;

import com.data.actor.ActorEntity;
import com.data.director.DirectorEntity;
import com.data.product.ProductEntity;

import java.util.Date;
import java.util.List;

/*
 * Created by dendy-prtha on 17/05/2019.
 * video entity
 */

public class VideoEntity {

    public int id;

    public ProductEntity product;

    public VideoType videoType;

    public Date releaseDate;

    public String studio;

    public int ageRating;

    public float avgRating;

    public int overallRank;

    public String streamUrl;

    public int duration;

    public List<ActorEntity> actors;

    public List<DirectorEntity> directors;

    public static class VideoType {

        public int id;

        public String code;

        public String name;
    }
}
