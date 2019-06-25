package com.data.album.repository.source.network.request;


/*
 * Created by dendy-prtha on 18/04/2019.
 * albums List Request DTO
 */

public class GetAlbumListRequest {
    public String authorization;

    public GetAlbumListRequest(String authorization)
    {
        this.authorization = authorization;
    }
}
