package com.ssafy.buttonup.domain.repository.invest;

import com.ssafy.buttonup.domain.model.entity.invest.StockStatus;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 주식 현황 레포지토리
 *
 * @author Jiun Kim
 * created on 2022-02-12
 */
public interface StockStatusRepository extends JpaRepository<StockStatus, Long> {
}