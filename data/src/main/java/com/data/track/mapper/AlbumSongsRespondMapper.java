package com.data.track.mapper;

import com.data.album.AlbumEntity;
import com.data.artist.ArtistEntity;
import com.data.product.ProductEntity;
import com.data.productimage.ProductImageEntity;
import com.data.track.TrackEntity;
import com.data.track.repository.source.network.response.FindUserBuyedSongsByAlbumIdResponse;
import com.data.track.repository.source.network.response.GetAlbumSongsResponse;
import com.data.track.repository.source.network.response.TrackListResponse;
import com.domain.base.entity.Album;
import com.domain.base.entity.Artist;
import com.domain.base.entity.Product;
import com.domain.base.entity.ProductImage;
import com.domain.base.entity.ProductImageType;
import com.domain.base.entity.Subcategory;
import com.domain.base.entity.Track;
import com.domain.track.SongResult;
import com.domain.track.TrackResult;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/*
 * Created by dendy-prtha on 18/04/2019.
 * Movie list response mapper
 */

@Singleton
public class AlbumSongsRespondMapper {

    @Inject
    public AlbumSongsRespondMapper() {

    }

    public List<SongResult> transform(GetAlbumSongsResponse response) {
        List<SongResult> result = new ArrayList<>();
        if (response != null && !response.albumSongList.isEmpty()) {
            for (ProductEntity songEntity : response.albumSongList) {
                SongResult songResult = new SongResult();
                songResult.song = new Product();
                songResult.song.id = songEntity.id;
                songResult.song.productName = songEntity.productName;
                songResult.song.packageCode = songEntity.packageCode;
                songResult.song.price = songEntity.price;
                songResult.song.description = songEntity.description;
                songResult.song.compatibility = songEntity.compatibility;
                songResult.song.status = songEntity.status;
                songResult.song.created = songEntity.created;

                if (songEntity.trackList != null) {
                    songResult.song.trackList = new ArrayList<>();
                    for (TrackEntity trackEntity : songEntity.trackList) {
                        Track track = new Track();
                        track.id = trackEntity.id;

                        track.streamUrl = trackEntity.streamUrl;
                        track.length = trackEntity.length;

                        if (trackEntity.trackType != null) {
                            track.trackType = new Track.TrackType();
                            track.trackType.id = trackEntity.trackType.id;
                            track.trackType.code = trackEntity.trackType.code;
                            track.trackType.name = trackEntity.trackType.name;
                        }

                        if(trackEntity.artists != null)
                        {
                            track.artists = new ArrayList<>();
                            for (ArtistEntity artistEntity : trackEntity.artists) {
                                Artist artist = new Artist();
                                artist.id = artistEntity.id;
                                artist.firstName = artistEntity.firstName;
                                artist.lastName = artistEntity.lastName;
                                track.artists.add(artist);
                            }
                        }
                        songResult.song.trackList.add(track);
                    }
                }

                if (songEntity.subcategory != null) {
                    songResult.song.subcategory = new Subcategory();
                    songResult.song.subcategory.id = songEntity.subcategory.id;
                    songResult.song.subcategory.name = songEntity.subcategory.name;
                    songResult.song.subcategory.created = songEntity.subcategory.created;
                    songResult.song.subcategory.isActive = songEntity.subcategory.isActive;
                    songResult.song.subcategory.target = songEntity.subcategory.target;
                    songResult.song.subcategory.priority = songEntity.subcategory.priority;
                }
                result.add(songResult);
            }
        }
        return result;
    }

    public List<SongResult> transform(FindUserBuyedSongsByAlbumIdResponse response) {
        List<SongResult> result = new ArrayList<>();
        if (response != null && !response.albumSongList.isEmpty()) {
            for (ProductEntity songEntity : response.albumSongList) {
                SongResult songResult = new SongResult();
                songResult.song = new Product();
                songResult.song.id = songEntity.id;
                songResult.song.productName = songEntity.productName;
                songResult.song.packageCode = songEntity.packageCode;
                songResult.song.price = songEntity.price;
                songResult.song.description = songEntity.description;
                songResult.song.compatibility = songEntity.compatibility;
                songResult.song.status = songEntity.status;
                songResult.song.created = songEntity.created;

                if (songEntity.trackList != null) {
                    songResult.song.trackList = new ArrayList<>();
                    for (TrackEntity trackEntity : songEntity.trackList) {
                        Track track = new Track();
                        track.id = trackEntity.id;

                        track.streamUrl = trackEntity.streamUrl;
                        track.length = trackEntity.length;

                        if (trackEntity.trackType != null) {
                            track.trackType = new Track.TrackType();
                            track.trackType.id = trackEntity.trackType.id;
                            track.trackType.code = trackEntity.trackType.code;
                            track.trackType.name = trackEntity.trackType.name;
                        }

                        if(trackEntity.artists != null)
                        {
                            track.artists = new ArrayList<>();
                            for (ArtistEntity artistEntity : trackEntity.artists) {
                                Artist artist = new Artist();
                                artist.id = artistEntity.id;
                                artist.firstName = artistEntity.firstName;
                                artist.lastName = artistEntity.lastName;
                                track.artists.add(artist);
                            }
                        }
                        songResult.song.trackList.add(track);
                    }
                }

                if (songEntity.subcategory != null) {
                    songResult.song.subcategory = new Subcategory();
                    songResult.song.subcategory.id = songEntity.subcategory.id;
                    songResult.song.subcategory.name = songEntity.subcategory.name;
                    songResult.song.subcategory.created = songEntity.subcategory.created;
                    songResult.song.subcategory.isActive = songEntity.subcategory.isActive;
                    songResult.song.subcategory.target = songEntity.subcategory.target;
                    songResult.song.subcategory.priority = songEntity.subcategory.priority;
                }
                result.add(songResult);
            }
        }
        return result;
    }
}