package com.appeal.api.portfolio.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TemplateTwo extends Portfolio {
    /**
     * TemplateTwo가 가진 project와 career는 생명주기를 완전히 TemplateTwo에 의존하며
     * 두 테이블을 직접 참조하는 경우는 없고 반드시 TemplateTwo를 통해서 참조되기 때문에
     * 양뱡향 연관관계 및 casecade옵션을 갖는다
     * 또한 연관관계 주인을 FK가 있는 쪽에 주어 변경에 닫히도록 했다(생성은 알아서 됨)
     */

    @OneToMany(mappedBy = "templateTwo", cascade = CascadeType.ALL)
    private List<TemplateTwoProject> projects = new ArrayList<>();

    @OneToMany(mappedBy = "templateTwo", cascade = CascadeType.ALL)
    private List<TemplateTwoCareer> careers = new ArrayList<>();
}
