package com.appeal.api.portfolio.repository;

import com.appeal.api.portfolio.domain.Portfolio;
import com.appeal.api.portfolio.domain.TemplateTwo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {
}
