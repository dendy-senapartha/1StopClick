package com.domain.base.entity;

import java.util.Date;
import java.util.List;

/*
 * Created by dendy-prtha on 17/05/2019.
 * actor
 */

public class Album {

    public int id;

    public String name;

    public Date releaseDate;

    public List<Track> tracks;

    public String albumImageUrl;

}
