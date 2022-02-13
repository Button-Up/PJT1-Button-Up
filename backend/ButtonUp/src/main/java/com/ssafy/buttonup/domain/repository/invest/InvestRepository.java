package com.ssafy.buttonup.domain.repository.invest;

import com.ssafy.buttonup.domain.model.entity.invest.Investment;
import com.ssafy.buttonup.domain.model.entity.invest.InvestPreset;
import com.ssafy.buttonup.domain.model.entity.user.Parent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 투자 레포지토리
 *
 * @author Jiun Kim
 * created on 2022-02-12
 */
public interface InvestRepository extends JpaRepository<Investment, Long> {
    /**
     * 부모 키로 투자 목록 조회
     *
     * @param parentSeq 부모 키
     * @return 투자 목록
     */
    List<Investment> findByParent_Seq(long parentSeq);

    /**
     * 동일한 투자 종목이 있는지 조회
     * @param target 종목 타겟
     * @param preset 종목 프리셋
     * @param parent 종목을 생성한 부모
     * @return 투자
     */
    Investment findByTargetAndInvestPresetAndParent(String target, InvestPreset preset, Parent parent);
}