package com.data.album.repository.source.network.response;

import com.data.account.HTTPResponseHeader;
import com.data.album.AlbumEntity;

import java.util.Date;
import java.util.List;

/*
 * Created by dendy-prtha on 18/04/2019.
 * Movie list response container
 */

public class GetUserBuyedAlbumResponse {

    public List<Album> albumList;
    public HTTPResponseHeader httpResponseHeader;
    public String exception;

    class Album{

        public int id;

        public String name;

        public Date releaseDate;

        public String albumImageUrl;
    }
}
