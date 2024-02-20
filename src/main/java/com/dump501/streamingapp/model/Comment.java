package com.dump501.streamingapp.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
public class Comment extends BaseModel{
    @Column(columnDefinition = "LONGTEXT")
    private String content;
    @ManyToOne(targetEntity = Video.class)
    private Video video;
    @ManyToOne(targetEntity = User.class)
    private User author;
    private Comment parentComment;
    private List<Comment> comments;
}
