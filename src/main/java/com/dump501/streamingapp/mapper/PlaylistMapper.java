package com.dump501.streamingapp.mapper;

import com.dump501.streamingapp.dto.PlaylistDto;
import com.dump501.streamingapp.dto.PlaylistStore;
import com.dump501.streamingapp.model.Playlist;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PlaylistMapper {
    PlaylistDto toDto(Playlist playlist);
    Playlist toEntity(PlaylistDto playlistDto);
    Playlist toEntity(PlaylistStore playlistStore);
    List<PlaylistDto> toDto(List<Playlist> playlists);
    List<Playlist> toEntity(List<PlaylistDto> playlistDtos);
}
