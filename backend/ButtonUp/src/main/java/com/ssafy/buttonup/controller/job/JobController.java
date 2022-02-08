package com.ssafy.buttonup.controller.job;

import com.ssafy.buttonup.domain.model.dto.job.request.JobHistoryRequest;
import com.ssafy.buttonup.domain.model.dto.job.request.JobRequest;
import com.ssafy.buttonup.domain.model.dto.job.response.JobResponse;
import com.ssafy.buttonup.domain.service.job.JobService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 직업 관련 컨트롤러
 *
 * @author jiun kim
 * created on 2022-02-05
 */
@Api(tags = "직업, 직업 내역 관련 기능")
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
     * @param parentSeq 부모 키
     * @return 직업 리스트
     */
    @GetMapping("parents/{parent_seq}")
    @ApiOperation(value = "직업 목록 조회", notes = "부모 키로 직업 목록을 조회합니다.")
    public ResponseEntity<List<JobResponse>> viewJobList(@ApiParam(value = "부모 키", required = true, example = "1") @PathVariable("parent_seq") long parentSeq) {
        return new ResponseEntity<>(jobService.getJobList(parentSeq), HttpStatus.OK);
    }

    /**
     * 자녀 키로 현재 직업 조회
     *
     * @param childSeq 자녀 키
     * @return 직업
     */
    @GetMapping("children/{child_seq}")
    @ApiOperation(value = "아이의 현재 직업 정보 조회", notes = "자녀 키로 현재 직업을 조회합니다.")
    public ResponseEntity<JobResponse> viewChildJob(@ApiParam(value = "자녀 키", required = true, example = "1") @PathVariable("child_seq") long childSeq) {
        return new ResponseEntity<>(jobService.getChildJob(childSeq), HttpStatus.OK);
    }

    /**
     * 직업 키로 직업 단일 조회
     *
     * @param jobSeq 직업 키
     * @return 현재 직업
     */
    @GetMapping("{job_seq}")
    @ApiOperation(value = "직업 정보 조회", notes = "직업 키로 직업을 조회합니다.")
    public ResponseEntity<JobResponse> viewJob(@ApiParam(value = "직업 키", required = true, example = "1") @PathVariable("job_seq") long jobSeq) {
        return new ResponseEntity<>(jobService.getJob(jobSeq), HttpStatus.OK);
    }

    /**
     * 새 직업 등록
     *
     * @param request 직업 등록 DTO
     * @return 성공여부
     */
    @PostMapping
    @ApiOperation(value = "새로운 직업 추가", notes = "새 직업을 등록합니다.")
    public void addJob(@ApiParam(value = "직업 추가 요청 정보", required = true) @RequestBody JobRequest request) {
        jobService.insertJob(request);
    }

    /**
     * 직업 내역 추가
     *
     * @param request 직업 내역 추가 DTO
     * @return 현재 직업
     */
    @PostMapping("histories")
    @ApiOperation(value = "직업 내역 추가", notes = "자녀에게 새로운 직업을 부여하면서 기존 직업을 업데이트하고 새로운 직업 내역을 추가합니다.")
    public ResponseEntity<JobResponse> addJobHistory(@ApiParam(value = "직업 내역 추가 요청 정보", required = true) @RequestBody JobHistoryRequest request) {
        return new ResponseEntity<>(jobService.insertJobHistory(request), HttpStatus.OK);
    }
}
