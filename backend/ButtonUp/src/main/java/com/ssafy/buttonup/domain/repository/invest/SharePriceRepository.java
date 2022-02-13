package com.ssafy.buttonup.domain.repository.invest;

import com.ssafy.buttonup.domain.model.entity.invest.SharePrice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 투자 시세(주가) 레포지토리
 *
 * @author Jiun Kim
 * created on 2022-02-12
 */
public interface SharePriceRepository extends JpaRepository<SharePrice, Long> {
    /**
     * 투자 종목으로 시세 목록 조회
     *
     * @param investSeq 투자 종목 키
     * @return 시세 목록
     */
    List<SharePrice> findByInvestment_SeqOrderByDateDesc(long investSeq);
}