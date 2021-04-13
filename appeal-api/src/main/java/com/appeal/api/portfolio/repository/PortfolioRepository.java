package com.appeal.api.portfolio.repository;

import com.appeal.api.member.domain.Member;
import com.appeal.api.portfolio.domain.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {

    List<Portfolio> findByMemberOrderByCreatedDateDesc(Member member);

    @Query("select p " +
            "from Portfolio as p " +
            "where p.skill " +
            "like %:keyword% " +
            "order by p.createdDate desc")
    List<Portfolio> findByKeyword(@Param("keyword") String keyword);
}
