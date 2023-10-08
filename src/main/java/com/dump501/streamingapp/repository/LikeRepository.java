package com.dump501.streamingapp.repository;

import com.dump501.streamingapp.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LikeRepository extends JpaRepository<Like, UUID> {
}
