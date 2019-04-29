package com.data.music.repository.source.network.response;

import com.data.account.HTTPResponseHeader;
import com.data.music.MusicEntity;

import java.util.List;

/*
 * Created by dendy-prtha on 26/04/2019.
 * Music list response
 */

public class MusicListResponse {

    public List<MusicEntity> musicList;
    public HTTPResponseHeader httpResponseHeader;
    public String exception;

    public MusicListResponse() {
        // Default constructor
    }
}
