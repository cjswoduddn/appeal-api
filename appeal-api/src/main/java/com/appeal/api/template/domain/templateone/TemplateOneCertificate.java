package com.appeal.api.template.domain.templateone;

import com.appeal.api.template.dto.templateone.TemplateOneCertificateDto;
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

    public static TemplateOneCertificate createTemplateOneCertificate(TemplateOneCertificateDto certificate, TemplateOne templateOne) {
        TemplateOneCertificate templateOneCertificate = new TemplateOneCertificate();
        templateOneCertificate.date = certificate.getDate();
        templateOneCertificate.title = certificate.getTitle();
        templateOneCertificate.origin = certificate.getOrigin();
        templateOneCertificate.templateOne = templateOne;
        return null;
    }
}
