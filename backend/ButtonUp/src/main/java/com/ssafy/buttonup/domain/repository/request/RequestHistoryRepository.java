package com.ssafy.buttonup.domain.repository.request;

import com.ssafy.buttonup.domain.model.entity.request.RequestHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 요청 내역 Repository
 * @author jeongyeon woo
 * created on 2022-02-07
 */

public interface RequestHistoryRepository extends JpaRepository<RequestHistory, Long> {
    /**
     * 아이 키로 요청 내역을 조회해 날짜로 오름차순 정렬시켜 요청 내역 목록 반환
     * @param childSeq
     * @return
     */
    List<RequestHistory> findByChild_SeqOrderByDateAsc(long childSeq);
}