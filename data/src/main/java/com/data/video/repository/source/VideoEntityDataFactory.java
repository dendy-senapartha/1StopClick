package com.data.video.repository.source;

import com.data.AbstractEntityDataFactory;
import com.data.video.repository.source.network.NetworkVideoEntityData;
import com.data.video.repository.source.network.VideoNetwork;

import javax.inject.Inject;

/*
 * Created by dendy-prtha on 11/06/2019.
 * Video Entity Data Factory
 */

public class VideoEntityDataFactory  extends AbstractEntityDataFactory {

    private final VideoNetwork videoNetwork;

    @Inject
    public VideoEntityDataFactory(VideoNetwork videoNetwork) {
        this.videoNetwork = videoNetwork;
    }


    @Override
    public VideoEntityData createData(String source) {
        return new NetworkVideoEntityData(videoNetwork);
    }
}
