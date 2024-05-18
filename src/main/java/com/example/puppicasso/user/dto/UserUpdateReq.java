package com.example.puppicasso.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserUpdateReq {
    @NotBlank(message = "password를 입력해주세요")
    @Size(min = 8, max = 50)
    private String password;

    public UserUpdateReq(String password)
    {
        this.password = password;
    }
}
