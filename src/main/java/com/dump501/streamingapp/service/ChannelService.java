package com.dump501.streamingapp.service;

import com.dump501.streamingapp.dto.ChannelDto;
import com.dump501.streamingapp.dto.ChannelStore;
import com.dump501.streamingapp.mapper.ChannelMapper;
import com.dump501.streamingapp.model.Channel;
import com.dump501.streamingapp.repository.ChannelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChannelService {
    private final ChannelRepository channelRepository;
    private final ChannelMapper channelMapper;

    public ChannelDto create(ChannelStore channelStore){
        return channelMapper.toDto(channelRepository.save(channelMapper.toEntity(channelStore)));
    }

    public List<ChannelDto> getAll(){
        return channelMapper.toDto(channelRepository.findAll());
    }
}
