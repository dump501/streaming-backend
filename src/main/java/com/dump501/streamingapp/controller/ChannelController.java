package com.dump501.streamingapp.controller;

import com.dump501.streamingapp.api.ChannelApi;
import com.dump501.streamingapp.dto.ChannelDto;
import com.dump501.streamingapp.dto.ChannelRequest;
import com.dump501.streamingapp.service.ChannelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ChannelController implements ChannelApi {
    private final ChannelService channelService;

    @Override
    public ResponseEntity<ChannelDto> createChannel(ChannelRequest channelRequest) {
        return ResponseEntity.ok(channelService.create(channelRequest));
    }


    @Override
    public ResponseEntity<List<ChannelDto>> getChannels() {
        return  ResponseEntity.ok(channelService.getAll());
    }
}
