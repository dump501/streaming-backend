package com.dump501.streamingapp.controller;

import com.dump501.streamingapp.api.ChannelApi;
import com.dump501.streamingapp.dto.ChannelDto;
import com.dump501.streamingapp.dto.ChannelRequest;
import com.dump501.streamingapp.model.User;
import com.dump501.streamingapp.service.ChannelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ChannelController implements ChannelApi {
    private final ChannelService channelService;

    @Override
    public ResponseEntity<ChannelDto> createChannel(ChannelRequest channelRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        channelRequest.setAuthor(user.getUuid());
        return ResponseEntity.ok(channelService.create(channelRequest));
    }


    @Override
    public ResponseEntity<List<ChannelDto>> getChannels() {
        return  ResponseEntity.ok(channelService.getAll());
    }

//    public ResponseEntity<List<ChannelDto>> getUserChannels(){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        User user = (User) authentication.getPrincipal();
//        return ResponseEntity.ok(channelService.getUserChannels(user.getUuid()));
//    }
}
