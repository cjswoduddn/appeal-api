package com.appeal.api.portfolio.domain;

import com.appeal.api.common.BaseTimeInfo;
import com.appeal.api.common.dto.portfolio.YoungWooTemplateOneStringDto;
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
    private String thumbnailUrl;
    private String title;
    private String skill;


    protected Portfolio(YoungWooTemplateOneStringDto convertedDto, Member member) {
        this.member = member;
        thumbnailUrl = convertedDto.getThumbnailUrl();
        title = convertedDto.getTitle();
        skill = convertedDto.getSkill();
    }
}
