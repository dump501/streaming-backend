package com.dump501.streamingapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "")
public class FrontendController {

    @GetMapping
    public Map<String, String> tik(){
        HashMap<String, String> status = new HashMap<>();
        status.put("message", "Server is up and running :)");
        return status;
    }
}
