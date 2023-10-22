package com.dump501.streamingapp.mapper;

import com.dump501.streamingapp.dto.CommentDto;
import com.dump501.streamingapp.dto.CommentStore;
import com.dump501.streamingapp.model.Comment;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    CommentDto toDto(Comment comment);
    Comment toEntity(CommentDto commentDto);
    Comment toEntity(CommentStore commentStore);
    List<CommentDto> toDto(List<Comment> comments);
    List<Comment> toEntity(List<CommentDto> commentDtos);
}
