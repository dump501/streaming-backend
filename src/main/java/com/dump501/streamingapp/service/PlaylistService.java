package com.dump501.streamingapp.service;

import com.dump501.streamingapp.dto.PlaylistDto;
import com.dump501.streamingapp.mapper.PlaylistMapper;
import com.dump501.streamingapp.repository.PlaylistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlaylistService {
    private final PlaylistRepository playlistRepository;
    private final PlaylistMapper playlistMapper;

    public PlaylistDto create(PlaylistDto playlistDto){
        return playlistMapper.toDto(playlistRepository.save(playlistMapper.toEntity(playlistDto)));
    }
}
