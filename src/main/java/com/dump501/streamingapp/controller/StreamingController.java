package com.dump501.streamingapp.controller;

import com.dump501.streamingapp.service.StreamingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor

public class StreamingController {
    private final StreamingService streamingService;

    @GetMapping("/stream/{uuid}")
    public ResponseEntity<byte[]> streamVideo(@RequestHeader(value = "Range", required = false) String httpRangeList, @PathVariable("uuid") UUID uuid){
        return streamingService.prepareContent(uuid, httpRangeList);
    }
}
