package com.dump501.streamingapp.controller;

import com.dump501.streamingapp.api.UserApi;
import com.dump501.streamingapp.dto.UserDto;
import com.dump501.streamingapp.dto.UserRequest;
import com.dump501.streamingapp.model.User;
import com.dump501.streamingapp.model.Video;
import com.dump501.streamingapp.service.UserService;
import com.dump501.streamingapp.service.VideoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Log4j2
public class UserController implements UserApi {
    private final UserService userService;
    private final VideoService videoService;


    @Override
    public ResponseEntity<UserDto> createUser(UserRequest userRequest) {
        return ResponseEntity.ok(userService.create(userRequest));
    }

    @Override
    public ResponseEntity<List<UserDto>> getUsers() {
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("/user/profile")
    public ResponseEntity<UserDto> profile() {
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if(authentication.isAuthenticated()){
                User user = (User) authentication.getPrincipal();
                return  ResponseEntity.ok(userService.findById(user.getUuid()));
            } else {
                return  ResponseEntity.badRequest().build();
            }
        }catch (ClassCastException e){
            log.info("error", e);
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (Exception e){
            log.info("error", e);
            return ResponseEntity.badRequest().build();
        }

    }

    @GetMapping("/user/like-video/{uuid}")
    public ResponseEntity<String> likeVideo(@PathVariable UUID uuid){
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if(authentication.isAuthenticated()){
                User user = (User) authentication.getPrincipal();
                if(videoService.likeVideo(uuid, user)){
                    return ResponseEntity.ok().build();
                } else {
                    return ResponseEntity.badRequest().build();
                }
            } else {
                return  new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e){
            log.info("error", e);
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/user/favorite-video/{uuid}")
    public ResponseEntity<String> favoriteVideo(@PathVariable UUID uuid){
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if(authentication.isAuthenticated()){
                User user = (User) authentication.getPrincipal();
                if(videoService.favoriteVideo(uuid, user)){
                    return ResponseEntity.ok().build();
                } else {
                    return ResponseEntity.badRequest().build();
                }
            } else {
                return  new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e){
            log.info("error", e);
            return ResponseEntity.badRequest().build();
        }
    }
}
