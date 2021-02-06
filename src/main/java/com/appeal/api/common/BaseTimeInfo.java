package com.appeal.api.common;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * @MappedSuperclass로 이 클래스를 상속한 모든 엔티티는 이 클래스의 필드를 공통으로 갖게 된다
 * @EnableJpaAuditing 를 main 위에 어노테이션으로 붙여 활성화시킨다
 * @EntityListeners(AuditingEntityListener.class)를 통해 이 클래스를 적용 대상으로 지정한다
 */
@MappedSuperclass @Getter @EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeInfo {

    @CreatedDate
    private LocalDateTime createdDate;
    @LastModifiedDate
    private LocalDateTime modifiedDate;
}
