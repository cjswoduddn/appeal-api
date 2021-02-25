package com.appeal.api.member.controller;

import com.appeal.api.common.dto.SignUpDto;
import com.appeal.api.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity signUp(@Valid @RequestBody SignUpDto dto){
        memberService.signUp(dto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("you can signin after email validation");
    }

    @GetMapping("/signup/{code}")
    public ResponseEntity vaildSignUp(@PathVariable("code") String code){
        memberService.validSignUp(code);
        return ResponseEntity
                .ok()
                .body("인증 성공! 로그인하세용");
    }
}
