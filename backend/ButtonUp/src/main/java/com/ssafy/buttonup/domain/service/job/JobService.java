package com.ssafy.buttonup.domain.service.job;

import com.ssafy.buttonup.domain.model.dto.job.request.JobHistoryRequest;
import com.ssafy.buttonup.domain.model.dto.job.request.JobRequest;
import com.ssafy.buttonup.domain.model.dto.job.response.JobResponse;

import java.util.List;

/**
 * 직업 관련 서비스 interface
 *
 * @author jiun kim
 * created on 2022-02-05
 */
public interface JobService {
    /**
     * 아이 현재 직업 조회
     * 
     * @param childSeq 아이 키
     * @return 직업
     */
    JobResponse getChildJob(long childSeq);

    /**
     * 부모의 직업 리스트 조회
     * 
     * @param parentSeq 부모 키
     * @return 직업 목록
     */
    List<JobResponse> getJobList(long parentSeq);

    /**
     * 직업 단일 조회
     * 
     * @param jobSeq 직업 키
     * @return 직업
     */
    JobResponse getJob(long jobSeq);

    /**
     * 새로운 직업 추가
     * 
     * @param request 새 직업 정보
     */
    void insertJob(JobRequest request);

    /**
     * 새로운 직업 내역 추가
     * 
     * @param request 직업 내역
     * @return 새 직업
     */
    JobResponse insertJobHistory(JobHistoryRequest request);
}
