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
public interface SavingHistoryRepository extends JpaRepository<SavingHistory, Long> {
    /**
     * 적금 최근 입금 내역 조회
     *
     * @param savingSeq
     * @return
     */
    SavingHistory findTopBySaving_SeqOrderByDateDesc(Long savingSeq);

    /**
     * 적금 내역 리스트 조회
     *
     * @param savingSeq
     * @return
     */
    List<SavingHistory> findBySaving_SeqOrderByDateDesc(long savingSeq);


}
