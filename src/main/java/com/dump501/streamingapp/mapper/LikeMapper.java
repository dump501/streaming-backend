package com.dump501.streamingapp.mapper;

import com.dump501.streamingapp.dto.LikeDto;
import com.dump501.streamingapp.dto.LikeStore;
import com.dump501.streamingapp.model.Like;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LikeMapper {
    LikeDto toDto(Like like);
    Like toEntity(LikeDto likeDto);
    Like toEntity(LikeStore likeStore);
    List<LikeDto> toDto(List<Like> likes);
    List<Like> toEntity(List<LikeDto> likeDtos);
}
