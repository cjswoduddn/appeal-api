package com.appeal.api.portfolio;

import com.appeal.api.common.BaseTimeInfo;
import com.appeal.api.member.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity @Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Portfolio extends BaseTimeInfo {

    @Id @GeneratedValue @Column(name = "PORTFOLIO_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    private String thumbnailUrl;

    private String title;

    private String skill;
}
