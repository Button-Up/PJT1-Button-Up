package com.ssafy.buttonup.domain.repository.job;

import com.ssafy.buttonup.domain.model.entity.job.JobHistory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 직업 내역 레포지토리
 *
 * @author jiun kim
 * created on 2022-02-06
 */
public interface JobHistoryRepository extends JpaRepository<JobHistory, Long> {
    /**
     * 가장 최근 직업 내역 조회
     *
     * @param childSeq 아이 키
     * @return 최근 직업 내역 조회     */
    JobHistory findTopByChild_SeqOrderBySeqDesc(long childSeq);
}