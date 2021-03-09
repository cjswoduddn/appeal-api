package com.appeal.api.portfolio.domain.templateone;

import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class TemplateOneCertificate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEMPLATE_ID")
    private TemplateOne templateOne;

    private String date;
    private String title;
    private String origin;
}
