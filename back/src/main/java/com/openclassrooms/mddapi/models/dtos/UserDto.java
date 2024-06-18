package com.openclassrooms.mddapi.models.dtos;

import org.springframework.lang.NonNull;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class UserDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String email;

    @NonNull
    private String username;

    private String createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NonNull
    public String getEmail() {
        return email;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
    }

    @NonNull
    public String getUsername() {
        return username;
    }

    public void setUsername(@NonNull String username) {
        this.username = username;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
