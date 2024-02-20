package com.dump501.streamingapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "users")
@Builder
public class User extends BaseModel implements UserDetails {
    private String name;
    @Column(unique = true)
    private String email;
    @JsonIgnore
    private String password;
    private String profile;
    private String bio;
    @ManyToOne(targetEntity = Role.class)
    private Role role;
    @OneToMany(mappedBy = "author")
    private List<Channel> channels;
    @OneToMany(mappedBy = "author")
    private List<Comment> comments;
    @ManyToMany(mappedBy = "favoriteBy")
    private List<Video> favoriteVideos;
    @ManyToMany(mappedBy = "likedBy")
    private List<Video> likedVideos;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
