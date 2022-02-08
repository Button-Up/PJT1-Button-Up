package com.ssafy.buttonup.domain.repository.job;

import com.ssafy.buttonup.domain.model.entity.job.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 직업 레포지토리
 *
 * @author jiun kim
 * created on 2022-02-05
 */
public interface JobRepository extends JpaRepository<Job, Long> {
    /**
     * 부모 키로 직업 리스트 조회
     * 
     * @param parentSeq 부모 키
     * @return 직업 리스트
     */
    List<Job> findByParent_SeqOrderBySeqDesc(long parentSeq);
}