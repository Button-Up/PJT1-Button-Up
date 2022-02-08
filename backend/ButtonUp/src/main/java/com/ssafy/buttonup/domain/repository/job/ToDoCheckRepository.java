package com.ssafy.buttonup.domain.repository.job;

import com.ssafy.buttonup.domain.model.entity.job.ToDoCheck;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 체크리스트 레포지토리
 *
 * @author SeungYeon Hwang
 * created on 2022-02-08
 */

public interface ToDoCheckRepository extends JpaRepository<ToDoCheck, Long> {

    /**
     * 아이 키와 할일 키로 체크리스트 조회
     *
     * @param childSeq,todoSeq 아이 키,할일 키
     * @return 체크리스트
     */

    ToDoCheck findByChild_SeqAndToDo_SeqOrderBySeqDesc(long childSeq,long todoSeq);
}
