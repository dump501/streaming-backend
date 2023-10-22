package com.dump501.streamingapp.mapper;

import com.dump501.streamingapp.dto.VideoDto;
import com.dump501.streamingapp.dto.VideoStore;
import com.dump501.streamingapp.model.Video;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VideoMapper {
    VideoDto toDto(Video video);
    Video toEntity(VideoDto videoDto);
    Video toEntity(VideoStore videoStore);
    List<VideoDto> toDto(List<Video> videos);
    List<Video> toEntity(List<VideoDto> videoDtos);
}
