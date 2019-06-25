package com.data.track.repository.source.network.response;

import com.data.account.HTTPResponseHeader;
import com.data.product.ProductEntity;

import java.util.List;

/*
 * Created by dendy-prtha on 18/04/2019.
 * Albums songs response container
 */

public class FindUserBuyedSongsByAlbumIdResponse {

    public List<ProductEntity> albumSongList;
    public HTTPResponseHeader httpResponseHeader;
    public String exception;

}
