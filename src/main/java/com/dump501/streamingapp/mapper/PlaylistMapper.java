package com.dump501.streamingapp.mapper;

import com.dump501.streamingapp.dto.PlaylistDto;
import com.dump501.streamingapp.dto.PlaylistRequest;
import com.dump501.streamingapp.model.Playlist;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring", uses = {ChannelMapper.class})
public interface PlaylistMapper {
    PlaylistDto toDto(Playlist playlist);
    Playlist toEntity(PlaylistDto playlistDto);
    @Mapping(source = "channel", target = "channel")
    Playlist toEntity(PlaylistRequest playlistRequest);
    List<PlaylistDto> toDto(List<Playlist> playlists);
    List<Playlist> toEntity(List<PlaylistDto> playlistDtos);

    default Playlist fromId(UUID uuid){
        if(uuid == null){
            return null;
        }
        Playlist playlist = new Playlist();
        playlist.setUuid(uuid);
        return playlist;
    }
}
