package com.dump501.streamingapp.controller;

import com.dump501.streamingapp.api.PlaylistApi;
import com.dump501.streamingapp.dto.PlaylistDto;
import com.dump501.streamingapp.dto.PlaylistRequest;
import com.dump501.streamingapp.service.PlaylistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class PlaylistController implements PlaylistApi {
    private final PlaylistService playlistService;


    @Override
    public ResponseEntity<PlaylistDto> createPlaylist(PlaylistRequest playlistRequest) {
        return ResponseEntity.ok(playlistService.create(playlistRequest));
    }

    @Override
    public ResponseEntity<List<PlaylistDto>> getPlaylists() {
        return ResponseEntity.ok(playlistService.getAll());
    }

    public ResponseEntity<PlaylistDto> getPlaylistDetail(@PathVariable UUID uuid){
        return  ResponseEntity.ok(playlistService.getByUuid(uuid));
    }
}
