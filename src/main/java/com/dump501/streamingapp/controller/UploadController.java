package com.dump501.streamingapp.controller;

import com.dump501.streamingapp.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UploadController {
    private final StorageService storageService;

    @PostMapping("/upload")
    public ResponseEntity<Map<String, String>> upload(@RequestParam("file")MultipartFile file){
        Map<String, String> response = new HashMap<>();
        response.put("file", this.storageService.upload(file));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/download/{imageName}")
    public ResponseEntity<Resource> download(@PathVariable("imageName") String imageName){
        Resource file = storageService.download(imageName);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+file.getFilename()+"\"")
                .body(file);
    }
}
