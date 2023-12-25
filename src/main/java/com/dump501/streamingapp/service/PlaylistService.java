package com.dump501.streamingapp.service;

import com.dump501.streamingapp.dto.PlaylistDto;
import com.dump501.streamingapp.dto.PlaylistRequest;
import com.dump501.streamingapp.dto.VideoDto;
import com.dump501.streamingapp.mapper.PlaylistMapper;
import com.dump501.streamingapp.model.Playlist;
import com.dump501.streamingapp.model.Video;
import com.dump501.streamingapp.repository.PlaylistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PlaylistService {
    private final PlaylistRepository playlistRepository;
    private final PlaylistMapper playlistMapper;

    public PlaylistDto create(PlaylistRequest playlistRequest){
        return playlistMapper.toDto(playlistRepository.save(playlistMapper.toEntity(playlistRequest)));
    }

    public List<PlaylistDto> getAll(){
        return playlistMapper.toDto(playlistRepository.findAll());
    }

    public PlaylistDto getByUuid(UUID uuid){
        Optional<Playlist> playlist = this.playlistRepository.findById(uuid);
        return playlist.map(this.playlistMapper::toDto).orElse(null);
    }
}
