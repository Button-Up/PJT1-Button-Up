package com.ssafy.buttonup.domain.service.job;

import com.ssafy.buttonup.domain.model.dto.job.request.JobHistoryRequest;
import com.ssafy.buttonup.domain.model.dto.job.request.JobRequest;
import com.ssafy.buttonup.domain.model.dto.job.response.JobResponse;
import com.ssafy.buttonup.domain.model.entity.job.Job;
import com.ssafy.buttonup.domain.model.entity.job.JobHistory;
import com.ssafy.buttonup.domain.model.entity.job.JobImage;
import com.ssafy.buttonup.domain.model.entity.job.ToDo;
import com.ssafy.buttonup.domain.repository.job.JobHistoryRepository;
import com.ssafy.buttonup.domain.repository.job.JobImageRepository;
import com.ssafy.buttonup.domain.repository.job.JobRepository;
import com.ssafy.buttonup.domain.repository.job.ToDoRepository;
import com.ssafy.buttonup.domain.repository.user.ChildRepository;
import com.ssafy.buttonup.domain.repository.user.ParentRepository;
import com.ssafy.buttonup.domain.service.common.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 직업 관련 서비스 구현체
 *
 * @author jiun kim
 * created on 2022-02-06
 */
@Service
@Transactional(readOnly = true)
public class JobServiceImpl extends ImageService implements JobService {
    private final JobRepository jobRepository;
    private final JobHistoryRepository jobHistoryRepository;
    private final JobImageRepository imageRepository;
    private final ChildRepository childRepository;
    private final ParentRepository parentRepository;
    private final ToDoRepository toDoRepository;

    @Autowired
    public JobServiceImpl(JobRepository jobRepository, JobHistoryRepository jobHistoryRepository, JobImageRepository imageRepository,
                          ChildRepository childRepository, ParentRepository parentRepository, ToDoRepository toDoRepository) {
        this.jobRepository = jobRepository;
        this.jobHistoryRepository = jobHistoryRepository;
        this.imageRepository = imageRepository;
        this.childRepository = childRepository;
        this.parentRepository = parentRepository;
        this.toDoRepository = toDoRepository;
    }

    /**
     * 아이 현재 직업 조회
     *
     * @param childSeq 아이 키
     * @return 직업
     */
    @Override
    public JobResponse getChildJob(long childSeq) {
        return Job.ToResponse(jobHistoryRepository.findTopByChild_SeqOrderBySeqDesc(childSeq).getJob());
    }


    /**
     * 부모의 직업 리스트 조회
     *
     * @param parentSeq 부모 키
     * @return 직업 목록
     */
    @Override
    public List<JobResponse> getJobList(long parentSeq) {
        List<Job> jobs = jobRepository.findByParent_SeqOrderBySeqDesc(parentSeq);
        List<JobResponse> list = new ArrayList<>();
        for (Job job : jobs) {
            list.add(Job.ToResponse(job));
        }
        return list;
    }

    /**
     * 직업 단일 조회
     *
     * @param jobSeq 직업 키
     * @return 직업
     */
    @Override
    public JobResponse getJob(long jobSeq) {
        return Job.ToResponse(jobRepository.getById(jobSeq));
    }

    /**
     * 새로운 직업 추가
     *
     * @param request 새 직업 정보
     */
    @Override
    @Transactional
    public void insertJob(JobRequest request) {
        // 직업 이미지 추가
        /*JobImage jobImage = null;
        JobImage.JobImageBuilder imageBuilder = JobImage.builder();
        try {
            imageBuilder.path(uploadFile(image));
            jobImage = imageBuilder.build();
            imageRepository.save(jobImage);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }*/

        long jobImageSeq = request.getJobImageSeq();
        JobImage jobImage = jobImageSeq == 0 ? null : imageRepository.getById(jobImageSeq);

        // 직업 추가
        Job job = Job.builder()
                .payTerm(request.getPayTerm())
                .pay(request.getPay())
                .name(request.getName())
                .parent(parentRepository.getById(request.getParentSeq()))
                .jobImage(jobImage)
                .build();
        jobRepository.save(job);

        // 직업 할일 리스트 추가

        for(String str: request.getToDoContents()){
            toDoRepository.save(ToDo.builder()
                    .content(str)
                    .job(job)
                    .build());
        }
    }

    /**
     * 새로운 직업 내역 추가
     *
     * @param request 직업 내역
     * @return 새 직업
     */
    @Override
    @Transactional
    public JobResponse insertJobHistory(JobHistoryRequest request) {
        // 기존 직업 내역 업데이트
        JobHistory jobHistory = jobHistoryRepository.findTopByChild_SeqOrderBySeqDesc(request.getChildSeq());

        if (jobHistory != null) { // 처음 직업 추가할 때 null 체크
            jobHistory.updateRecentJobHistory();
            jobHistoryRepository.save(jobHistory);
        }

        // 새로운 직업 내역 추가
        JobHistory newJobHistory = JobHistory.builder()
                .endDate(null)
                .job(jobRepository.getById(request.getJobSeq()))
                .child(childRepository.getById(request.getChildSeq()))
                .build();
        jobHistoryRepository.save(newJobHistory);

        // TODO: 새로운 직업에 대한 할일 체크리스트 추가하기

        return getJob(request.getJobSeq());
    }

}
