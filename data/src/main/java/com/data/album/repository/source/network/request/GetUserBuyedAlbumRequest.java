package com.data.album.repository.source.network.request;


/*
 * Created by dendy-prtha on 18/04/2019.
 * get buyed album Request DTO
 */

public class GetUserBuyedAlbumRequest {
    public String authorization;
    public String userId;

    public GetUserBuyedAlbumRequest(String authorization, String userId) {
        this.authorization = authorization;
        this.userId = userId;
    }
}
