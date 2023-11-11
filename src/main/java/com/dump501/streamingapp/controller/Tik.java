package com.dump501.streamingapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Tik {
    @GetMapping("/tik")
    public String tik(){
        return "tik";
    }
}
