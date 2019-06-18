package com.data.track.repository.source;

import com.data.AbstractEntityDataFactory;
import com.data.track.repository.source.network.NetworkTrackEntityData;
import com.data.track.repository.source.network.TrackNetwork;

import javax.inject.Inject;

/*
 * Created by dendy-prtha on 11/06/2019.
 * Track Entity Data Factory
 */

public class TrackEntityDataFactory extends AbstractEntityDataFactory {

    private final TrackNetwork trackNetwork;

    @Inject
    public TrackEntityDataFactory(TrackNetwork videoNetwork) {
        this.trackNetwork = videoNetwork;
    }


    @Override
    public TrackEntityData createData(String source) {
        return new NetworkTrackEntityData(trackNetwork);
    }
}
