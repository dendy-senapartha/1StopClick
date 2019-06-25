package com.data.video.mapper;

import com.data.actor.ActorEntity;
import com.data.director.DirectorEntity;
import com.data.video.VideoEntity;
import com.data.video.repository.source.network.response.VideoListResponse;
import com.domain.base.entity.Actor;
import com.domain.base.entity.Director;
import com.domain.base.entity.Product;
import com.domain.base.entity.Video;
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
public class VideoRespondMapper {

    @Inject
    public VideoRespondMapper() {

    }

    public List<VideoResult> transform(VideoListResponse response) {
        List<VideoResult> result = new ArrayList<>();
        if (response != null && !response.videoEntityList.isEmpty()) {
            for (VideoEntity videoEntity : response.videoEntityList) {
                VideoResult videoResult = new VideoResult();
                videoResult.video = new Video();
                videoResult.video.id = videoEntity.id;
                videoResult.video.releaseDate = videoEntity.releaseDate;
                videoResult.video.studio = videoEntity.studio;
                videoResult.video.ageRating = videoEntity.ageRating;
                videoResult.video.avgRating = videoEntity.avgRating;
                videoResult.video.overallRank = videoEntity.overallRank;
                videoResult.video.streamUrl = videoEntity.streamUrl;
                videoResult.video.duration = videoEntity.duration;

                videoResult.video.videoType = new Video.VideoType();
                if (videoEntity.videoType != null) {
                    videoResult.video.videoType.id = videoEntity.videoType.id;
                    videoResult.video.videoType.code = videoEntity.videoType.code;
                    videoResult.video.videoType.name = videoEntity.videoType.name;
                }

                if (videoEntity.actors != null) {
                    videoResult.video.actors = new ArrayList<>();
                    for (ActorEntity actorEntity : videoEntity.actors) {
                        Actor actor = new Actor();
                        actor.id = actorEntity.id;
                        actor.firstName = actorEntity.firstName;
                        actor.lastName = actorEntity.lastName;
                        videoResult.video.actors.add(actor);
                    }
                }

                if (videoEntity.directors != null) {
                    videoResult.video.directors = new ArrayList<>();
                    for (DirectorEntity directorEntity: videoEntity.directors) {
                        Director director = new Director();
                        director.id = directorEntity.id;
                        director.firstName = directorEntity.firstName;
                        director.lastName = directorEntity.lastName;
                        videoResult.video.directors.add(director);
                    }
                }

                if (videoEntity.product != null) {
                    videoResult.video.product = new Product();
                }

                result.add(videoResult);
            }
        }
        return result;
    }
}