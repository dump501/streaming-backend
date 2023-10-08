package com.dump501.streamingapp.model;

import com.fasterxml.jackson.annotation.JsonInclude;
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
public class Channel extends BaseModel {
    private String title;
    private String subTitle;
    private String description;
    private String profile;
    @ManyToOne(targetEntity = User.class)
    private User author;
    @OneToMany(mappedBy = "channel")
    private List<Playlist> playlists;
    @OneToMany(mappedBy = "channel")
    private List<Video> videos;
}
