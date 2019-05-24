package com.data.track;

import com.data.album.AlbumEntity;
import com.data.artist.ArtistEntity;
import com.data.product.ProductEntity;

import java.util.Date;
import java.util.List;

/*
 * Created by dendy-prtha on 17/05/2019.
 * track entity
 */

public class TrackEntity {

    public int id;

    public ProductEntity product;

    public TrackType trackType;

    public String streamUrl;

    public int length;

    public List<AlbumEntity> albums;

    public List<ArtistEntity> artists;

    public static class TrackType {

        public int id;

        public String code;

        public String name;
    }
}
