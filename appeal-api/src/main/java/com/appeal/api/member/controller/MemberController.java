package com.appeal.api.member.controller;

import com.appeal.api.member.dto.SignUpDto;
import com.appeal.api.member.dto.UpdateMemberDto;
import com.appeal.api.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity signUp(@Valid @RequestBody SignUpDto dto){
        memberService.signUp(dto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("you can signin after email validation");
    }

    @GetMapping("/{code}")
    public ResponseEntity vaildSignUp(@PathVariable("code") String code){
        memberService.validSignUp(code);
        return ResponseEntity
                .ok()
                .body("인증 성공! 로그인하세용");
    }

    /**
     * @PathVariable은 애당초 URL패턴에 해당해서 @NotBlank를 붙일 필요가 없다
     * id값이 없는 요청이 온다면 서블릿이 에러를 뱉을 것이다
     * 이 경우에는 405 method not found를 뱉을 것이다
     */
    @PutMapping("/{id}")
    public ResponseEntity updateMemberInfo(@PathVariable("id") Long id,
                                           @Valid @RequestBody UpdateMemberDto dto){
        memberService.updateMemberInfo(id, dto);
        return ResponseEntity
                .ok()
                .body("변경이 완료되었습니다!");
    }

}
