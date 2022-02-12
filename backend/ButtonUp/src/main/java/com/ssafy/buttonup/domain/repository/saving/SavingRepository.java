package com.ssafy.buttonup.domain.repository.saving;


import com.ssafy.buttonup.domain.model.entity.saving.SavingHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 적금 내역 레포지토리
 *
 * @author eungchol kim
 * created on 2022-02-12
 */
public interface SavingRepository extends JpaRepository<SavingHistory, Long> {
    /**
     * 적금 잔액만 조회
     *
     * @param childSeq 자녀 코드
     * @return 적금 잔액
     */
//    SavingHistory findTopByChild_SeqOrderByDateDesc(long childSeq);

    /**
     * 적금 내역 리스트 조회
     *
     * @param childSeq 자녀 코드
     * @return 적금 내역 목록
     */
//    List<SavingHistory> findByChild_SeqOrOrderByDateDesc(long childSeq);

}
