package com.appeal.api.template.repository;

import com.appeal.api.template.domain.templateone.TemplateOne;
import com.appeal.api.template.domain.templatetwo.TemplateTwo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TemplateOneRepository extends JpaRepository<TemplateOne, Long> {
    @Query("select t " +
            "from TemplateOne as t " +
            "join fetch t.portfolio " +
            "where t.id = :id")
    Optional<TemplateOne> findById(@Param("id") Long id);
}
