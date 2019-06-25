package com.data.track.repository.source.network.request;


/*
 * Created by dendy-prtha on 18/04/2019.
 * track List Request DTO
 */

public class FindUserBuyedSongsByAlbumIdRequest {
    public String authorization;
    public String userId;
    public String albumId;

    public FindUserBuyedSongsByAlbumIdRequest(String authorization, String userId, String albumId) {
        this.authorization = authorization;
        this.userId = userId;
        this.albumId = albumId;
    }
}
