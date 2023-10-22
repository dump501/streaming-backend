package com.dump501.streamingapp.service;

import com.dump501.streamingapp.dto.VideoDto;
import com.dump501.streamingapp.dto.VideoStore;
import com.dump501.streamingapp.mapper.VideoMapper;
import com.dump501.streamingapp.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VideoService {
    private final VideoRepository videoRepository;
    private final VideoMapper videoMapper;

    public VideoDto create(VideoStore videoDto){
        return videoMapper.toDto(videoRepository.save(videoMapper.toEntity(videoDto)));
    }
}
