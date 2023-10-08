package com.dump501.streamingapp.service;

import com.dump501.streamingapp.dto.CommentDto;
import com.dump501.streamingapp.mapper.CommentMapper;
import com.dump501.streamingapp.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    public CommentDto create(CommentDto commentDto){
        return commentMapper.toDto(commentRepository.save(commentMapper.toEntity(commentDto)));
    }
}
