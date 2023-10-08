package com.dump501.streamingapp.repository;

import com.dump501.streamingapp.model.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PlaylistRepository extends JpaRepository<Playlist, UUID> {
}
