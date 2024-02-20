package com.dump501.streamingapp.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
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
public class Video extends BaseModel{
    private String title;
    private String name;
    private String description;
    private String thumbnail;
    @ManyToOne(targetEntity = Channel.class)
    private Channel channel;
    @ManyToOne(targetEntity = Playlist.class)
    private Playlist playlist;
    @OneToMany(mappedBy = "video")
    private List<Comment> comments;
    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "video_uuid"), inverseJoinColumns = @JoinColumn(name = "user_uuid"))
    private List<User> favoriteBy;
    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "video_uuid"), inverseJoinColumns = @JoinColumn(name = "user_uuid"))
    private List<User> likedBy;
}
