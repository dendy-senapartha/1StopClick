package com.domain.base.entity;

import java.util.Date;
import java.util.List;

/*
 * Created by dendy-prtha on 17/05/2019.
 * video entity
 */

public class Video {

    public int id;

    public Product product;

    public VideoType videoType;

    public Date releaseDate;

    public String studio;

    public int ageRating;

    public float avgRating;

    public int overallRank;

    public String streamUrl;

    public int duration;

    public List<Actor> actors;

    public List<Director> directors;

    public static class VideoType {

        public int id;

        public String code;

        public String name;
    }
}
