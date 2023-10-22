package com.dump501.streamingapp.controller;

import com.dump501.streamingapp.api.PlaylistApi;
import com.dump501.streamingapp.dto.PlaylistDto;
import com.dump501.streamingapp.dto.PlaylistStore;
import com.dump501.streamingapp.service.PlaylistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PlaylistController implements PlaylistApi {
    private final PlaylistService playlistService;
    @Override
    public ResponseEntity<PlaylistDto> createPlaylist(PlaylistStore playlistStore) {
        return ResponseEntity.ok(playlistService.create(playlistStore));
    }

    @Override
    public ResponseEntity<List<PlaylistDto>> getPlaylists() {
        return ResponseEntity.ok(playlistService.getAll());
    }
}
