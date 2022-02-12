package com.ssafy.buttonup.domain.repository.invest;

import com.ssafy.buttonup.domain.model.entity.invest.InvestStatus;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 투자 현황 레포지토리
 *
 * @author Jiun Kim
 * created on 2022-02-12
 */
public interface InvestStatusRepository extends JpaRepository<InvestStatus, Long> {
}