package com.dump501.streamingapp.controller;

import com.dump501.streamingapp.api.CommentApi;
import com.dump501.streamingapp.dto.CommentDto;
import com.dump501.streamingapp.dto.CommentRequest;
import com.dump501.streamingapp.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController implements CommentApi {
    private final CommentService commentService;
    @Override
    public ResponseEntity<CommentDto> createComment(CommentRequest commentRequest) {
        return ResponseEntity.ok(commentService.create(commentRequest));
    }

    @Override
    public ResponseEntity<List<CommentDto>> getComments() {
        return CommentApi.super.getComments();
    }
}
