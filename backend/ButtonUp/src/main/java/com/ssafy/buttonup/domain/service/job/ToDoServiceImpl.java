package com.ssafy.buttonup.domain.service.job;

import com.ssafy.buttonup.domain.model.dto.job.response.ToDoResponse;
import com.ssafy.buttonup.domain.model.entity.job.ToDo;
import com.ssafy.buttonup.domain.repository.job.ToDoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 할일 관련 서비스 구현체
 *
 * @author SeungYeon Hwang
 * created on 2022-02-06
 */

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ToDoServiceImpl implements ToDoService{

    private final ToDoRepository toDoRepository;

    /**
     * 할일 리스트 조회
     *
     * @param jobSeq 직업 키
     * @return 할일 목록
     */
    @Override
    public List<ToDoResponse> getToDoList(long jobSeq) {
        List<ToDo> toDos = toDoRepository.findByJob_SeqOrderBySeqDesc(jobSeq);
        List<ToDoResponse> list = new ArrayList<>();
        for(ToDo toDo:toDos){
            ToDoResponse toDoResponse = ToDoResponse.builder()
                    .seq(toDo.getSeq())
                    .content(toDo.getContent())
                    .build();
            list.add(toDoResponse);
        }
        return list;
    }
}
