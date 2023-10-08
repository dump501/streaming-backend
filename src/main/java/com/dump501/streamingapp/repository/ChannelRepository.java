package com.dump501.streamingapp.repository;

import com.dump501.streamingapp.model.Channel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ChannelRepository extends JpaRepository<Channel, UUID> {
}
