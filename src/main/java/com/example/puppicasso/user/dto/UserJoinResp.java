package com.example.puppicasso.user.dto;

import com.example.puppicasso.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserJoinResp {
    private Long id;
    private String username;

    public UserJoinResp(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
    }
}
