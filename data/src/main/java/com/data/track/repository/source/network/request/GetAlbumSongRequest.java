package com.data.track.repository.source.network.request;


/*
 * Created by dendy-prtha on 18/04/2019.
 * track List Request DTO
 */

public class GetAlbumSongRequest {
    public String authorization;
    public String albumId;

    public GetAlbumSongRequest(String authorization, String albumId) {
        this.authorization = authorization;
        this.albumId = albumId;
    }
}
