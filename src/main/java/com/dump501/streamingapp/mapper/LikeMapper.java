package com.dump501.streamingapp.mapper;

import com.dump501.streamingapp.dto.LikeDto;
import com.dump501.streamingapp.dto.LikeRequest;
import com.dump501.streamingapp.model.Like;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring", uses = {UserMapper.class, VideoMapper.class, CommentMapper.class})
public interface LikeMapper {
    LikeDto toDto(Like like);
    Like toEntity(LikeDto likeDto);
    @Mapping(source = "user", target = "user")
    @Mapping(source = "video", target = "video")
    @Mapping(source = "comment", target = "comment")
    Like toEntity(LikeRequest likeRequest);
    List<LikeDto> toDto(List<Like> likes);
    List<Like> toEntity(List<LikeDto> likeDtos);

    default Like fromId(UUID uuid){
        if(uuid == null){
            return null;
        }
        Like like = new Like();
        like.setUuid(uuid);
        return  like;
    }
}
