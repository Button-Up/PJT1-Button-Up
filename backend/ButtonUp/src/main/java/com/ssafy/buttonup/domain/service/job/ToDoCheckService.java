package com.ssafy.buttonup.domain.service.job;

import com.ssafy.buttonup.domain.model.dto.job.response.ToDoCheckResponse;
import com.ssafy.buttonup.domain.model.entity.job.JobHistory;
import com.ssafy.buttonup.domain.model.entity.job.ToDo;
import com.ssafy.buttonup.domain.model.entity.job.ToDoCheck;
import com.ssafy.buttonup.domain.repository.job.JobHistoryRepository;
import com.ssafy.buttonup.domain.repository.job.ToDoCheckRepository;
import com.ssafy.buttonup.domain.repository.job.ToDoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

    public List<ToDoCheckResponse> getCheckList(long childSeq){
        //현재 직업 가져오기
        JobHistory jobHistory = jobHistoryRepository.findTopByChild_SeqOrderBySeqDesc(childSeq);
        //현재 직업의 할일 가져오기
        List<ToDo> toDos = toDoRepository.findByJob_SeqOrderBySeqDesc(jobHistory.getSeq());

        List<ToDoCheckResponse> list = new ArrayList<>();

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
}
