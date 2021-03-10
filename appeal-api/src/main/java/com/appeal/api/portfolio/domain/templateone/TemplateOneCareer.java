package com.appeal.api.portfolio.domain.templateone;


import com.appeal.api.portfolio.dto.template.templateone.TemplateOneCareerDto;
import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class TemplateOneCareer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEMPLATE_ID")
    private TemplateOne templateOne;

    private String date;
    private String title;
    private String position;
    private String name;

    public static TemplateOneCareer createTemplateOneCareer(TemplateOneCareerDto career, TemplateOne templateOne) {
        TemplateOneCareer templateOneCareer = new TemplateOneCareer();
        templateOneCareer.date = career.getDate();
        templateOneCareer.title = career.getTitle();
        templateOneCareer.position = career.getPosition();
        templateOneCareer.name = career.getName();
        templateOneCareer.templateOne = templateOne;
        return templateOneCareer;
    }
}
