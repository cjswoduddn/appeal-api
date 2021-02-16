package com.appeal.api.portfolio;

import com.appeal.api.common.BaseTimeInfo;
import com.appeal.api.member.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.reflect.FieldUtils;

import javax.persistence.*;
import java.util.Map;

@Entity @Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
public class Portfolio extends BaseTimeInfo {

    @Id @GeneratedValue @Column(name = "PORTFOLIO_ID")
    private Long id;

    @ManyToOne//(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;
    private String thumbnailUrl;
    private String title;
    private String skill;

    public static Portfolio createPortfolio(Map<String, String> map, Member member) {
        Portfolio portfolio = PortfolioFactory.createPortfolio(map.get("dtype"));
        portfolio.member = member;
        portfolio.thumbnailUrl = map.get("thumbnail");
        portfolio.title = map.get("title");
        portfolio.skill = map.get("skill");
        portfolio.doInnerSetter(map);
        return portfolio;
    }

    /**
     * map을 효과적으로 반복하지 못하고 각각의 필드에 일일이 대입하는 식으로 코딩했었다.
     * 그런데, map에는 인스턴스의 필드명과 정확하게 일치하는(스펙을 그렇게 맞췄으니까)
     * key값에 value가 저장되어 있고 이를 잘 활용할 방법을 생각해봤다.
     * 역시나 해답은 존재하고, 요즘 공부하고 있는 reflection을 이용해서
     * String으로 필드를 찾고 값을 세팅할 수 있었다.
     * 좀더 편리한 사용을 위해 FieldUtils 의존성을 추가해 작업했다.
     * todo : 도메인 스펙과 클라이언트가 강하게 연결된 방식이다. 중간 계층을 통해 유연함을 확보하면 좋을 것 같다.
     */
    protected void doInnerSetter(Map<String, String> map){
        map.forEach((key, value) -> {
            try {
                FieldUtils.writeField(this, key, value, true);
            } catch (IllegalAccessException e) {
            } catch(IllegalArgumentException e){
            }
        });
    };
}
