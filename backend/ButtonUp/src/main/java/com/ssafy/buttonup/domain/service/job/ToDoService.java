package com.ssafy.buttonup.domain.service.job;

import com.ssafy.buttonup.domain.model.dto.job.response.ToDoResponse;

import java.util.List;

/**
 * 할일 관련 서비스 interface
 *
 * @author SeungYeon Hwang
 * created on 2022-02-06
 */

public interface ToDoService {

    /**
     * 할일 리스트 조회
     *
     * @param jobSeq 직업 키
     * @return 할일 목록
     */
    List<ToDoResponse> getToDoList(long jobSeq);
}
