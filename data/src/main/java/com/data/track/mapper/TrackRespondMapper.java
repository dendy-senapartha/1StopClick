package com.data.track.mapper;

import com.data.actor.ActorEntity;
import com.data.album.AlbumEntity;
import com.data.artist.ArtistEntity;
import com.data.director.DirectorEntity;
import com.data.track.TrackEntity;
import com.data.track.repository.source.network.response.TrackListResponse;
import com.data.video.VideoEntity;
import com.domain.base.entity.Actor;
import com.domain.base.entity.Album;
import com.domain.base.entity.Artist;
import com.domain.base.entity.Director;
import com.domain.base.entity.Product;
import com.domain.base.entity.Track;
import com.domain.base.entity.Video;
import com.domain.track.TrackResult;
import com.domain.video.VideoResult;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/*
 * Created by dendy-prtha on 18/04/2019.
 * Movie list response mapper
 */

@Singleton
public class TrackRespondMapper {

    @Inject
    public TrackRespondMapper() {

    }

    public List<TrackResult> transform(TrackListResponse response) {
        List<TrackResult> result = new ArrayList<>();
        if (response != null && !response.trackEntityList.isEmpty()) {
            for (TrackEntity trackEntity : response.trackEntityList) {
                TrackResult trackResult = new TrackResult();
                trackResult.track = new Track();
                trackResult.track.id = trackEntity.id;

                trackResult.track.streamUrl = trackEntity.streamUrl;
                trackResult.track.length = trackEntity.length;

                if (trackEntity.trackType != null) {
                    trackResult.track.trackType = new Track.TrackType();
                    trackResult.track.trackType.id = trackEntity.trackType.id;
                    trackResult.track.trackType.code = trackEntity.trackType.code;
                    trackResult.track.trackType.name = trackEntity.trackType.name;
                }

                trackResult.track.artists = new ArrayList<>();
                for (ArtistEntity artistEntity : trackEntity.artists) {
                    Artist artist = new Artist();
                    artist.id = artistEntity.id;
                    artist.firstName = artistEntity.firstName;
                    artist.lastName = artistEntity.lastName;
                    trackResult.track.artists.add(artist);
                }

                trackResult.track.albums = new ArrayList<>();
                for (AlbumEntity albumEntity : trackEntity.albums) {
                    Album album = new Album();
                    album.id = albumEntity.id;
                    album.name = albumEntity.name;
                    album.releaseDate = albumEntity.releaseDate;
                    album.albumImageUrl = albumEntity.albumImageUrl;
                    trackResult.track.albums.add(album);
                }
                trackResult.track.product = new Product();
                result.add(trackResult);
            }
        }
        return result;
    }
}