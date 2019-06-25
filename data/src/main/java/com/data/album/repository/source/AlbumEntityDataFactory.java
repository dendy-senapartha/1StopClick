package com.data.album.repository.source;

import com.data.AbstractEntityDataFactory;
import com.data.album.repository.source.network.NetworkAlbumEntityData;
import com.data.album.repository.source.network.AlbumNetwork;

import javax.inject.Inject;

/*
 * Created by dendy-prtha on 16/04/2019.
 * Album Entity Data factory
 */

public class AlbumEntityDataFactory extends AbstractEntityDataFactory {

    private final AlbumNetwork movieNetwork;

    @Inject
    public AlbumEntityDataFactory(AlbumNetwork movieNetwork) {
        this.movieNetwork = movieNetwork;
    }


    @Override
    public AlbumEntityData createData(String source) {
        return new NetworkAlbumEntityData(movieNetwork);
    }
}
