package com.appeal.api.template.domain.templatetwo;

import com.appeal.api.template.dto.templatetwo.TemplateTwoCareerDto;
import lombok.*;

import javax.persistence.*;

@Builder @AllArgsConstructor
@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class TemplateTwoCareer {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEMPLATE_ID")
    private TemplateTwo templateTwo;

    private String title;
    private String date;
    private String position;
    private String stack;
    private String intro;

    public static TemplateTwoCareer createTemplateTwoCareer(TemplateTwoCareerDto career, TemplateTwo templateTwo) {
        return TemplateTwoCareer
                .builder()
                .title(career.getTitle())
                .date(career.getDate())
                .intro(career.getIntro())
                .position(career.getPosition())
                .stack(career.getStack())
                .templateTwo(templateTwo)
                .build()
                ;
    }
}
