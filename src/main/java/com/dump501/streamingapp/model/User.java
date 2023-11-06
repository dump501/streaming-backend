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
@Table(name = "users")
public class User extends BaseModel {
    private String name;
    @Column(unique = true)
    private String email;
    private String password;
    private String profile;
    private String bio;
    @ManyToOne(targetEntity = Role.class)
    private Role role;
    @OneToMany(mappedBy = "author")
    private List<Channel> channels;
    @OneToMany(mappedBy = "author")
    private List<Comment> comments;
    @OneToMany
    private List<Like> likes;

}
