package com.ssafy.buttonup.controller.job;

import com.ssafy.buttonup.domain.model.dto.job.request.JobHistoryRequest;
import com.ssafy.buttonup.domain.model.dto.job.request.JobRequest;
import com.ssafy.buttonup.domain.model.dto.job.response.JobResponse;
import com.ssafy.buttonup.domain.service.job.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 직업 관련 컨트롤러
 *
 * @author jiun kim
 * created on 2022-02-05
 */
@RestController
@RequestMapping("jobs")
public class JobController {
    private final JobService jobService;

    @Autowired
    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    /**
     * 부모가 가진 직업 리스트 조회
     *
     * @param parent_seq 부모 키
     * @return 직업 리스트
     */
    @GetMapping
    public ResponseEntity<List<JobResponse>> viewJobList(@RequestParam long parent_seq){
        return new ResponseEntity<>(jobService.getJobList(parent_seq), HttpStatus.OK);
    }

    /**
     * 아이 키로 현재 직업 조회
     *
     * @param child_seq 아이 키
     * @return 직업
     */
    @GetMapping("{child_seq}/current")
    public ResponseEntity<JobResponse> viewChildJob(@PathVariable long child_seq) {
        return new ResponseEntity<>(jobService.getChildJob(child_seq), HttpStatus.OK);
    }

    /**
     * 직업 키로 직업 단일 조회
     *
     * @param job_seq 직업 키
     * @return 현재 직업
     */
    @GetMapping("{job_seq}")
    public ResponseEntity<JobResponse> viewJob(@PathVariable long job_seq) {
        return new ResponseEntity<>(jobService.getJob(job_seq), HttpStatus.OK);
    }

    /**
     * 새 직업 등록
     * 
     * @param request 직업 등록 DTO
     * @return 성공여부
     */
    @PostMapping
    public HttpStatus addJob(@RequestBody JobRequest request) {
        jobService.insertJob(request);
        return HttpStatus.OK;
    }

    /**
     * 직업 삭제
     *
     * @param job_seq 삭제하는 직업 키
     * @return 성공여부
     */
    @DeleteMapping
    public HttpStatus removeJob(@RequestBody long job_seq) {
        jobService.deleteJob(job_seq);
        return HttpStatus.OK;
    }

    /**
     * 직업 내역 추가
     *
     * @param request 직업 내역 추가 DTO
     * @return 현재 직업
     */
    @PostMapping("histories")
    public ResponseEntity<JobResponse> addJobHistory(@RequestBody JobHistoryRequest request) {
        return new ResponseEntity<>(jobService.insertJobHistory(request), HttpStatus.OK);
    }
}
