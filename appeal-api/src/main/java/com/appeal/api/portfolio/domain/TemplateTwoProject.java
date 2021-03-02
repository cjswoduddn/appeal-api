package com.appeal.api.portfolio.domain;

import com.appeal.api.portfolio.dto.TemplateTwoProjectDto;
import lombok.*;

import javax.persistence.*;

@Builder @AllArgsConstructor
@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class TemplateTwoProject {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PORTFOLIO_ID")
    private TemplateTwo templateTwo;

    private String name;
    private String intro;
    private String role;
    private String thumbnail;

    public static TemplateTwoProject createTemplateTwoProject(TemplateTwoProjectDto project, TemplateTwo templateTwo) {
        return
                TemplateTwoProject
                .builder()
                .intro(project.getIntro())
                .name(project.getName())
                .role(project.getRole())
                .thumbnail(project.getThumbnail())
                .templateTwo(templateTwo)
                .build()
                ;
    }
}
