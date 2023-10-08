package com.dump501.streamingapp.mapper;

import com.dump501.streamingapp.dto.ChannelDto;
import com.dump501.streamingapp.model.Channel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ChannelMapper {
    ChannelDto toDto(Channel channel);
    Channel toEntity(ChannelDto channelDto);
    List<ChannelDto> toDto(List<Channel> channels);
    List<Channel> toEntity(List<ChannelDto> channelDtos);
}
