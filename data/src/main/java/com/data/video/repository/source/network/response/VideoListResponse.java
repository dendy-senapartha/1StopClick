package com.data.video.repository.source.network.response;

import com.data.account.HTTPResponseHeader;
import com.data.video.VideoEntity;

import java.util.List;

/*
 * Created by dendy-prtha on 18/04/2019.
 * Movie list response container
 */

public class VideoListResponse {

    public List<VideoEntity> videoEntityList;
    public HTTPResponseHeader httpResponseHeader;
    public String exception;

    public VideoListResponse() {
        // Default constructor
    }
}
