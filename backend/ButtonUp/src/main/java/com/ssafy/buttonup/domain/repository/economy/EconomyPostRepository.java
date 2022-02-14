package com.ssafy.buttonup.domain.repository.economy;

import com.ssafy.buttonup.domain.model.entity.economy.EconomyPost;
import com.ssafy.buttonup.domain.model.entity.economy.EconomyTopic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 경제 사전 레포지토리
 *
 * @author Eunee Chung
 * created on 2022-02-14
 */

public interface EconomyPostRepository extends JpaRepository<EconomyPost, Long> {
    /**
     * 경제 주제별 내용 리스트 조회
     *
     * @return 경제 주제 리스트
     */
    List<EconomyPost> findByTopic_SeqOrderByOrder(long topicSeq);

}