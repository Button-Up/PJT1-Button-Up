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
    /**
     * 투자 키와 자녀 키로 투자 현황 조회
     *
     * @param investSeq 투자 키
     * @param childSeq  자녀 키
     * @return 투자 현황 엔티티
     */
    InvestStatus findByInvestment_SeqAndChild_Seq(long investSeq, long childSeq);
}