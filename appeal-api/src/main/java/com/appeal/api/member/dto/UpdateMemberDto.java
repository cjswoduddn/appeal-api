package com.appeal.api.member.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter @Setter
public class UpdateMemberDto {
    @NotBlank
    private String password;
    @NotBlank
    private String name;
}
