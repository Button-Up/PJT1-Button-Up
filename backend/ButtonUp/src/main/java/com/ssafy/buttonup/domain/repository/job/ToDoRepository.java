package com.ssafy.buttonup.domain.repository.job;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 할일 레포지토리
 *
 * @author SeungYeon Hwang
 * created on 2022-02-06
 */
public interface ToDoRepository extends JpaRepository<ToDo, Long> {
    /**
     * 직업 키로 할일 리스트 조회
     *
     * @param jobSeq 직업 키
     * @return 할일 리스트
     */
    List<ToDo> findByJob_SeqOrderBySeqDesc(long jobSeq);
}
