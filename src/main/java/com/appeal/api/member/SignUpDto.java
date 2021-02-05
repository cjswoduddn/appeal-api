package com.appeal.api.member;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class SignUpDto {

    @Email @NotEmpty
    private String email;
    @NotEmpty
    private String password;
    @NotEmpty
    private String name;
}
