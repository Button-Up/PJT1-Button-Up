package com.ssafy.buttonup.domain.service.job;

import com.ssafy.buttonup.domain.model.dto.job.request.JobHistoryRequest;
import com.ssafy.buttonup.domain.model.dto.job.request.JobRequest;
import com.ssafy.buttonup.domain.model.dto.job.response.JobResponse;
import com.ssafy.buttonup.domain.model.dto.job.response.ToDoResponse;
import com.ssafy.buttonup.domain.model.entity.job.*;
import com.ssafy.buttonup.domain.repository.job.*;
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
public class JobService extends ImageService {
    private final JobRepository jobRepository;
    private final JobHistoryRepository jobHistoryRepository;
    private final JobImageRepository imageRepository;
    private final ChildRepository childRepository;
    private final ParentRepository parentRepository;
    private final ToDoRepository toDoRepository;
    private final ToDoCheckRepository toDoCheckRepository;

    @Autowired
    public JobService(JobRepository jobRepository, JobHistoryRepository jobHistoryRepository, JobImageRepository imageRepository,
                      ChildRepository childRepository, ParentRepository parentRepository, ToDoRepository toDoRepository, ToDoCheckRepository toDoCheckRepository) {
        this.jobRepository = jobRepository;
        this.jobHistoryRepository = jobHistoryRepository;
        this.imageRepository = imageRepository;
        this.childRepository = childRepository;
        this.parentRepository = parentRepository;
        this.toDoRepository = toDoRepository;
        this.toDoCheckRepository = toDoCheckRepository;
    }

    /**
     * 아이 현재 직업 조회
     *
     * @param childSeq 아이 키
     * @return 직업
     */
    public JobResponse getChildJob(long childSeq) {
        return Job.ToResponse(jobHistoryRepository.findTopByChild_SeqOrderBySeqDesc(childSeq).getJob());
    }


    /**
     * 부모의 직업 리스트 조회
     *
     * @param parentSeq 부모 키
     * @return 직업 목록
     */

    @Transactional
    public List<JobResponse> getJobList(long parentSeq) {
        List<Job> jobs = jobRepository.findByParent_SeqOrderBySeqDesc(parentSeq);
        List<JobResponse> list = new ArrayList<>();

        for (Job job : jobs) {
            JobResponse jobResponse = Job.ToResponse(job);

            List<ToDo> toDos = toDoRepository.findByJob_SeqOrderBySeqDesc(job.getSeq());

            List<ToDoResponse> toDoResponses = new ArrayList<>();
            for(ToDo toDo: toDos){
                toDoResponses.add(ToDoResponse.builder()
                        .seq(toDo.getSeq())
                        .content(toDo.getContent())
                        .build());
            }
            jobResponse.setToDos(toDoResponses);
            list.add(jobResponse);
        }

        return list;
    }

    /**
     * 직업 단일 조회
     *
     * @param jobSeq 직업 키
     * @return 직업
     */
    public JobResponse getJob(long jobSeq) {

        Job job = jobRepository.getById(jobSeq);
        JobResponse jobResponse = Job.ToResponse(job);

        List<ToDo> toDos = toDoRepository.findByJob_SeqOrderBySeqDesc(job.getSeq());

        List<ToDoResponse> toDoResponses = new ArrayList<>();
        for(ToDo toDo: toDos){
            toDoResponses.add(ToDoResponse.builder()
                    .seq(toDo.getSeq())
                    .content(toDo.getContent())
                    .build());
        }
        jobResponse.setToDos(toDoResponses);

        return jobResponse;
    }

    /**
     * 새로운 직업 추가(지언) 및 할일 생성(승연)
     *
     * @param request 새 직업 정보
     */
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
                .jobImage(jobImage)
                .parent(parentRepository.getById(request.getParentSeq()))
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
     * 새로운 직업 내역 추가(지언) 및 체크리스트 생성(승연)
     *
     * @param request 직업 내역
     * @return 새 직업
     */
    @Transactional
    public JobResponse insertJobHistory(JobHistoryRequest request) {        //직업 키,. 아이 키
        // 기존 직업 내역 업데이트
        JobHistory jobHistory = jobHistoryRepository.findTopByChild_SeqOrderBySeqDesc(request.getChildSeq());

        if (jobHistory != null) { // 처음 직업 추가할 때 null 체크
            jobHistory.updateRecentJobHistory();
            jobHistoryRepository.save(jobHistory);
        }

        // 새로운 직업 내역 추가
        JobHistory newJobHistory = JobHistory.builder()
                .endDate(null)
                .child(childRepository.getById(request.getChildSeq()))
                .job(jobRepository.getById(request.getJobSeq()))
                .build();
        jobHistoryRepository.save(newJobHistory);

        // TODO: 새로운 직업에 대한 할일 체크리스트 추가하기

        //request에 있는 직업키로 할일 리스트 조회
        List<ToDo> toDos = toDoRepository.findByJob_SeqOrderBySeqDesc(request.getJobSeq());

        //리스트 돌면서 체크리스트 생성
        for(ToDo toDo : toDos){
            toDoCheckRepository.save(ToDoCheck.builder()
                    .flag(false)
                    .toDo(toDo)
                    .child(childRepository.getById(request.getChildSeq()))
                    .build());
        }

        return getJob(request.getJobSeq());
    }

}
