package com.dump501.streamingapp.controller;

import com.dump501.streamingapp.api.VideoApi;
import com.dump501.streamingapp.dto.VideoDto;
import com.dump501.streamingapp.dto.VideoRequest;
import com.dump501.streamingapp.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class VideoController implements VideoApi {
    private final VideoService videoService;
    @Override
    public ResponseEntity<VideoDto> createVideo(VideoRequest videoRequest) {
        return ResponseEntity.ok(videoService.create(videoRequest));
    }

    @Override
    public ResponseEntity<List<VideoDto>> getVideos() {
        return ResponseEntity.ok(videoService.getAll());
    }
}
