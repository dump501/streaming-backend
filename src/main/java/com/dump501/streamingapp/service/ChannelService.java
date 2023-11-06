package com.dump501.streamingapp.service;

import com.dump501.streamingapp.dto.ChannelDto;
import com.dump501.streamingapp.dto.ChannelRequest;
import com.dump501.streamingapp.mapper.ChannelMapper;
import com.dump501.streamingapp.repository.ChannelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChannelService {
    private final ChannelRepository channelRepository;
    private final ChannelMapper channelMapper;

    public ChannelDto create(ChannelRequest channelRequest){
        return channelMapper.toDto(channelRepository.save(channelMapper.toEntity(channelRequest)));
    }

    public List<ChannelDto> getAll(){
        return channelMapper.toDto(channelRepository.findAll());
    }
}
