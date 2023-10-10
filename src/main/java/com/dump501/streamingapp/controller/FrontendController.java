package com.dump501.streamingapp.controller;

import com.dump501.streamingapp.api.FrontApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FrontendController implements FrontApi {
    @Override
    public ResponseEntity<String> getMessage() {
        return ResponseEntity.ok("Server is up and running");
    }
}
