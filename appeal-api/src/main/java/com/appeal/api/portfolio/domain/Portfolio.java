package com.appeal.api.portfolio.domain;

import com.appeal.api.common.BaseTimeInfo;
import com.appeal.api.portfolio.dto.PortfolioDto;
import com.appeal.api.member.domain.Member;
import lombok.*;

import javax.persistence.*;

@Builder @AllArgsConstructor
@Entity @Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
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
    private String templateType;

    public static Portfolio createPortfolio(PortfolioDto portfolio, String templateType, Member member) {
        return
                Portfolio.builder()
                .member(member)
                .intro(portfolio.getIntro())
                .name(portfolio.getName())
                .skill(portfolio.getSkill())
                .templateType(templateType)
                .thumbnail(portfolio.getThumbnail())
                .title(portfolio.getTitle())
                .build()
                ;
    }
}
