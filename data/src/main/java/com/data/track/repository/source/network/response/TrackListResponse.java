package com.data.track.repository.source.network.response;

import com.data.account.HTTPResponseHeader;
import com.data.track.TrackEntity;

import java.util.List;

/*
 * Created by dendy-prtha on 18/04/2019.
 * Track list response container
 */

public class TrackListResponse {

    public List<TrackEntity> trackEntityList;
    public HTTPResponseHeader httpResponseHeader;
    public String exception;

    public TrackListResponse() {
        // Default constructor
    }
}
