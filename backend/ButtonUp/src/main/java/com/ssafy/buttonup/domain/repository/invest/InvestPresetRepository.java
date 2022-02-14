package com.ssafy.buttonup.domain.repository.invest;

import com.ssafy.buttonup.domain.model.entity.invest.InvestPreset;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 투자 프리셋 레포지토리
 *
 * @author Jiun Kim
 * created on 2022-02-12
 */
public interface InvestPresetRepository extends JpaRepository<InvestPreset, Long> {
}