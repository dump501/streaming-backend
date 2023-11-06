package com.dump501.streamingapp.mapper;

import com.dump501.streamingapp.dto.CommentDto;
import com.dump501.streamingapp.dto.CommentRequest;
import com.dump501.streamingapp.model.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring", uses = {VideoMapper.class, UserMapper.class})
public interface CommentMapper {
    CommentDto toDto(Comment comment);
    Comment toEntity(CommentDto commentDto);
    @Mapping(source = "author", target = "author")
    @Mapping(source = "video", target = "video")
    Comment toEntity(CommentRequest commentRequest);
    List<CommentDto> toDto(List<Comment> comments);
    List<Comment> toEntity(List<CommentDto> commentDtos);

    default Comment fromId(UUID uuid){
        if (uuid == null){
            return null;
        }
        Comment comment1 = new Comment();
        comment1.setUuid(uuid);
        return comment1;
    }
}
