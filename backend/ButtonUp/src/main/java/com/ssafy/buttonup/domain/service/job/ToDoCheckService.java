package com.ssafy.buttonup.domain.service.job;

import com.ssafy.buttonup.domain.model.dto.job.request.ToDoCheckRequest;
import com.ssafy.buttonup.domain.model.dto.job.response.ToDoCheckResponse;
import com.ssafy.buttonup.domain.model.entity.job.JobHistory;
import com.ssafy.buttonup.domain.model.entity.job.ToDo;
import com.ssafy.buttonup.domain.model.entity.job.ToDoCheck;
import com.ssafy.buttonup.domain.model.entity.user.Child;
import com.ssafy.buttonup.domain.repository.job.JobHistoryRepository;
import com.ssafy.buttonup.domain.repository.job.ToDoCheckRepository;
import com.ssafy.buttonup.domain.repository.job.ToDoRepository;
import com.ssafy.buttonup.domain.repository.user.ChildRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 체크리스트 관련 서비스
 *
 * @author SeungYeon Hwang
 * created on 2022-02-08
 */

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ToDoCheckService {

    private final ToDoCheckRepository toDoCheckRepository;
    private final JobHistoryRepository jobHistoryRepository;
    private final ToDoRepository toDoRepository;

    /**
     * 체크 리스트 조회
     *
     * @param childSeq 아이 키
     * @return 체크리스트
     */

    @Transactional
    public List<ToDoCheckResponse> getCheckList(long childSeq){

        List<ToDoCheckResponse> list = new ArrayList<>();

        //현재 직업 가져오기
        JobHistory jobHistory = jobHistoryRepository.findTopByChild_SeqOrderBySeqDesc(childSeq);
        if(jobHistory==null){
            ToDoCheckResponse toDoCheckResponse = ToDoCheckResponse.builder()
                    .seq(-1)
                    .content("직업을 먼저 설정해주세요!")
                    .build();
            list.add(toDoCheckResponse);
            return list;
        }
        //현재 직업의 할일 가져오기
        List<ToDo> toDos = toDoRepository.findByJob_SeqOrderBySeqDesc(jobHistory.getJob().getSeq());
        if(jobHistory==null){
            ToDoCheckResponse toDoCheckResponse = ToDoCheckResponse.builder()
                    .seq(-1)
                    .content("할일이 없네요ㅠㅠ 직업에서 할일을 추가해주세요!")
                    .build();
            list.add(toDoCheckResponse);
            return list;
        }

        //체크리스트 가져오고 dto 만들기
        for(ToDo toDo : toDos ) {
            ToDoCheck toDoCheck = toDoCheckRepository.findByChild_SeqAndToDo_SeqOrderBySeqDesc(childSeq, toDo.getSeq());
            ToDoCheckResponse toDoCheckResponse = ToDoCheckResponse.builder()
                    .seq(toDoCheck.getSeq())
                    .content(toDo.getContent())
                    .flag(toDoCheck.isFlag())
                    .build();
            list.add(toDoCheckResponse);
        }
        return list;
    }

    /**
     * 체크 리스트 완료 여부 수정
     *
     * @param toDoCheckRequest 체크리스트 요청 Dto
     */

    @Transactional
    public void updateCheckList(ToDoCheckRequest toDoCheckRequest){
        //체크리스트 가져오기
        ToDoCheck toDoCheck = toDoCheckRepository.getById(toDoCheckRequest.getCheckListSeq());

        toDoCheck.changeFlag(toDoCheckRequest.isFlag());
        toDoCheckRepository.save(toDoCheck);
    }
}
