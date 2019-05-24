package com.data.artist;

import com.data.track.TrackEntity;

import java.util.Date;
import java.util.List;

/*
 * Created by dendy-prtha on 17/05/2019.
 * artist
 */

public class ArtistEntity {

    public int id;

    public String firstName;

    public String lastName;

    public Date dob;

    private List<TrackEntity> tracks;
}
