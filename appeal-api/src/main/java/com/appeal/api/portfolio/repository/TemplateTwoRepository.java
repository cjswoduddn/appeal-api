package com.appeal.api.portfolio.repository;

import com.appeal.api.portfolio.domain.templatetwo.TemplateTwo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TemplateTwoRepository extends JpaRepository<TemplateTwo, Long> {

    @Query("select t " +
            "from TemplateTwo as t " +
            "join fetch t.portfolio")
    Optional<TemplateTwo> findById(Long id);
}
