package com.ssafy.buttonup.domain.repository.invest;

import com.ssafy.buttonup.domain.model.entity.invest.SharePrice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SharePriceRepository extends JpaRepository<SharePrice, Long> {
}