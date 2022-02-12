package com.ssafy.buttonup.domain.repository.invest;

import com.ssafy.buttonup.domain.model.entity.invest.StockStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockStatusRepository extends JpaRepository<StockStatus, Long> {
}