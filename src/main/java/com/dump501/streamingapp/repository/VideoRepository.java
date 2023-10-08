package com.dump501.streamingapp.repository;

import com.dump501.streamingapp.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VideoRepository extends JpaRepository<Video, UUID> {
}
