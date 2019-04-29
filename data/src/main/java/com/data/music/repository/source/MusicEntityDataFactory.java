package com.data.music.repository.source;

import com.data.AbstractEntityDataFactory;
import com.data.music.repository.source.network.MusicNetwork;
import com.data.music.repository.source.network.NetworkMusicEntityData;

import javax.inject.Inject;
/*
 * Created by dendy-prtha on 26/04/2019.
 * Music data factory implementation
 */

public class MusicEntityDataFactory extends AbstractEntityDataFactory {

    private final MusicNetwork musicNetwork;

    @Inject
    public MusicEntityDataFactory(MusicNetwork musicNetwork) {
        this.musicNetwork = musicNetwork;
    }

    @Override
    public NetworkMusicEntityData createData(String source) {
        return new NetworkMusicEntityData(musicNetwork);
    }
}
