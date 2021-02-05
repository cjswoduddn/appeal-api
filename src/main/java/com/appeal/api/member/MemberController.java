package com.appeal.api.member;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
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
                .ok()
                .body("인증 후 로그인이 가능합니다");
    }

    @GetMapping("/signup/{code}")
    public ResponseEntity vaildSignUp(@PathVariable("code") String code){
        memberService.validSignUp(code);
        return ResponseEntity
                .ok()
                .body("인증 성공! 로그인하세용");
    }


}
