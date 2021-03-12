package com.appeal.api.member.controller;

import com.appeal.api.member.dto.MemberSession;
import com.appeal.api.member.dto.MemberDto;
import com.appeal.api.member.dto.UpdateMemberDto;
import com.appeal.api.member.dto.AuthenticatedMember;
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
    public ResponseEntity signUp(@Valid @RequestBody MemberDto dto){
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

    @GetMapping
    public ResponseEntity<MemberDto> getMember(@AuthenticatedMember MemberSession memberSession){
        return ResponseEntity
                .ok()
                .body(MemberDto.createMemberDto(memberSession))
                ;
    }

    @PatchMapping
    public ResponseEntity updateMemberInfo(@AuthenticatedMember MemberSession memberSession, UpdateMemberDto dto){
        memberService.updateMemberInfo(memberSession, dto);
        return ResponseEntity
                .ok()
                .body("변경 완료!");
    }

}
