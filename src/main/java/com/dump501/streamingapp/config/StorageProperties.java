package com.dump501.streamingapp.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties("storage")
@RequiredArgsConstructor
@Getter
@Setter
@Component
public class StorageProperties {
    private final String location = "public";
}
