package com.dump501.streamingapp.mapper;

import com.dump501.streamingapp.dto.ChannelDto;
import com.dump501.streamingapp.dto.ChannelRequest;
import com.dump501.streamingapp.model.Channel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface ChannelMapper {
    ChannelDto toDto(Channel channel);
    Channel toEntity(ChannelDto channelDto);
    @Mapping(source = "author", target = "author")
    Channel toEntity(ChannelRequest channelRequest);
    List<ChannelDto> toDto(List<Channel> channels);
    List<Channel> toEntity(List<ChannelDto> channelDtos);

    default Channel fromId(UUID uuid){
        if(uuid == null){
            return null;
        }
        Channel channel = new Channel();
        channel.setUuid(uuid);
        return channel;
    }
}
