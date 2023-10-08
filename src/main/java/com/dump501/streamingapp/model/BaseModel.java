package com.dump501.streamingapp.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@MappedSuperclass
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    protected UUID uuid;

    protected LocalDateTime createdAt;
    protected Boolean deleted;
    protected LocalDateTime deletedAt;

    protected BaseModel() {
        this.createdAt = LocalDateTime.now();
        this.deleted = Boolean.FALSE;
    }
}
