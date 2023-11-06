package com.dump501.streamingapp.mapper;

import com.dump501.streamingapp.dto.VideoDto;
import com.dump501.streamingapp.dto.VideoRequest;
import com.dump501.streamingapp.model.Video;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring", uses = {ChannelMapper.class, PlaylistMapper.class})
public interface VideoMapper {
    VideoDto toDto(Video video);
    Video toEntity(VideoDto videoDto);
    @Mapping(source = "channel", target = "channel")
    @Mapping(source = "playlist", target = "playlist")
    Video toEntity(VideoRequest videoRequest);
    List<VideoDto> toDto(List<Video> videos);
    List<Video> toEntity(List<VideoDto> videoDtos);

    default Video fromId(UUID uuid){
        if(uuid == null){
            return null;
        }
        Video video = new Video();
        video.setUuid(uuid);
        return video;
    }
}
