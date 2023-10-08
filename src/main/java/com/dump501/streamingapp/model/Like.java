package com.dump501.streamingapp.model;

import com.dump501.streamingapp.enums.LikeEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "likes")
public class Like extends BaseModel{
    @ManyToOne(targetEntity = User.class)
    private User user;
    @ManyToOne(targetEntity = Video.class)
    private Video video;
    @ManyToOne(targetEntity = Comment.class)
    private Comment comment;
    private LikeEnum likeType;
}
