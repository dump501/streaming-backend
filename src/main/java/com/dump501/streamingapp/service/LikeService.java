package com.dump501.streamingapp.service;

import com.dump501.streamingapp.dto.LikeDto;
import com.dump501.streamingapp.dto.LikeRequest;
import com.dump501.streamingapp.mapper.LikeMapper;
import com.dump501.streamingapp.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;
    private final LikeMapper likeMapper;

    public LikeDto create(LikeRequest likeRequest){
        return likeMapper.toDto(likeRepository.save(likeMapper.toEntity(likeRequest)));
    }
}
