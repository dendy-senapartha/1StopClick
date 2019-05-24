package com.domain.base.entity;
import java.util.List;

/*
 * Created by dendy-prtha on 17/05/2019.
 * track entity
 */

public class Track {

    public int id;

    public Product product;

    public TrackType trackType;

    public String streamUrl;

    public int length;

    public List<Album> albums;

    public List<Artist> artists;

    public static class TrackType {

        public int id;

        public String code;

        public String name;
    }
}
