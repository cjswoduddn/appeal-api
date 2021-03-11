package com.appeal.api.portfolio.repository;

import com.appeal.api.member.domain.Member;
import com.appeal.api.portfolio.domain.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {
    List<Portfolio> findByMember(Member member);
}
