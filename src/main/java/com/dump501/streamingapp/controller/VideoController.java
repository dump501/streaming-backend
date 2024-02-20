package com.dump501.streamingapp.controller;

import com.dump501.streamingapp.api.VideoApi;
import com.dump501.streamingapp.dto.VideoDto;
import com.dump501.streamingapp.dto.VideoRequest;
import com.dump501.streamingapp.model.User;
import com.dump501.streamingapp.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

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

    @GetMapping("/video/{uuid}")
    public ResponseEntity<VideoDto> getVideoDetail(@PathVariable UUID uuid){
        return ResponseEntity.ok(videoService.getByUuid(uuid));
    }

}
