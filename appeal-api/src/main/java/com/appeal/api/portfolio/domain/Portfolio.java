package com.appeal.api.portfolio.domain;

import com.appeal.api.common.BaseTimeInfo;
import com.appeal.api.common.dto.portfolio.PortfolioDto;
import com.appeal.api.member.domain.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity @Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
public class Portfolio extends BaseTimeInfo {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "PORTFOLIO_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    private String thumbnail;
    private String title;
    private String skill;
    private String name;
    private String intro;

    protected Portfolio(PortfolioDto dto, Member member){
        this.member = member;
        thumbnail = dto.getThumbnail();
        title = dto.getTitle();
        skill = dto.getSkill();
        name = dto.getName();
        intro = dto.getIntro();
    }

}
