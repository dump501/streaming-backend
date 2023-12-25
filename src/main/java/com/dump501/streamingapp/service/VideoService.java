package com.dump501.streamingapp.service;

import com.dump501.streamingapp.dto.VideoDto;
import com.dump501.streamingapp.dto.VideoRequest;
import com.dump501.streamingapp.mapper.VideoMapper;
import com.dump501.streamingapp.model.Video;
import com.dump501.streamingapp.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VideoService {
    private final VideoRepository videoRepository;
    private final VideoMapper videoMapper;

    public VideoDto create(VideoRequest videoRequest){
        return videoMapper.toDto(videoRepository.save(videoMapper.toEntity(videoRequest)));
    }

    public List<VideoDto> getAll(){
        return videoMapper.toDto(videoRepository.findAll());
    }

    public Video getById(UUID uuid){
        Optional<Video> post = this.videoRepository.findById(uuid);
        return post.orElse(null);
    }

    public VideoDto getByUuid(UUID uuid){
        Optional<Video> video = this.videoRepository.findById(uuid);
        return video.map(this.videoMapper::toDto).orElse(null);
    }
}
