package com.data.album;

import com.data.track.TrackEntity;

import java.util.Date;
import java.util.List;

/*
 * Created by dendy-prtha on 17/05/2019.
 * actor
 */

public class AlbumEntity {

    public int id;

    public String name;

    public Date releaseDate;

    public List<TrackEntity> tracks;

    public String albumImageUrl;
}
