package com.appeal.api.member.controller;

import com.appeal.api.member.dto.MemberSession;
import com.appeal.api.member.dto.MemberDto;
import com.appeal.api.member.dto.UpdateMemberDto;
import com.appeal.api.member.dto.AuthenticatedMember;
import com.appeal.api.member.service.MemberService;
import com.appeal.code.SuccessCode;
import com.appeal.dto.SuccessResponse;
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
    public ResponseEntity<SuccessResponse> signUp(@Valid @RequestBody MemberDto dto){
        memberService.signUp(dto);
        return new ResponseEntity<>(SuccessResponse.of(SuccessCode.CREATE_MEMBER), HttpStatus.CREATED);
    }

    @GetMapping("/{code}")
    public ResponseEntity<SuccessResponse> vaildSignUp(@PathVariable("code") String code){
        memberService.validSignUp(code);
        return new ResponseEntity<>(SuccessResponse.of(SuccessCode.VALID_MEMBER), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<SuccessResponse<MemberDto>> getMyInfomation(@AuthenticatedMember MemberSession memberSession){
        return new ResponseEntity<>
                (SuccessResponse.of(SuccessCode.SUCCESS_GET_MY_INFOMATION,
                        MemberDto.createMemberDto(memberSession)), HttpStatus.OK)
                ;
    }

    @PatchMapping
    public ResponseEntity<SuccessResponse> updateMemberInfo(@AuthenticatedMember MemberSession memberSession,
                                                           @RequestBody UpdateMemberDto dto){
        memberService.updateMemberInfo(memberSession, dto);
        return new ResponseEntity<>(
                SuccessResponse.of(SuccessCode.SUCCESS_UPDATE_MEMBER),
                HttpStatus.OK
        );
    }

}
