package com.appeal.api.portfolio.domain.templateone;

import com.appeal.api.portfolio.domain.Portfolio;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TemplateOne {
    @Id
    @Column(name = "TEMPLATE_ID")
    private Long id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "TEMPLATE_ID")
    private Portfolio portfolio;

    @OneToMany(mappedBy = "templateOne", cascade = CascadeType.ALL)
    private List<TemplateOneCertificate> certificates = new ArrayList<>();

    @OneToMany(mappedBy = "templateOne", cascade = CascadeType.ALL)
    private List<TemplateOneCareer> careers = new ArrayList<>();

    private String birth;
    private String englishName;
    private String phone;
    private String address;
    private String email;
    private String highschoolDate;
    private String highschoolName;
    private String highschoolMajor;
    private String highschoolGraduation;
    private String highschoolScroe;
    private String colleageDate;
    private String colleageName;
    private String colleageMajor;
    private String colleageScore;
    private String colleageGraduation;
    private String graduateschoolDate;
    private String graduateschoolName;
    private String graduateschoolMajor;
    private String graduateschoolGraduation;
    private String graduateschoolScore;
    private String militaryStatus;


    private static String getTemplateType() {
        return "templateone";
    }
}
