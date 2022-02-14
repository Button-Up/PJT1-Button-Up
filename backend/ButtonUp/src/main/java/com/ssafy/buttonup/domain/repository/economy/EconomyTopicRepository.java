package com.ssafy.buttonup.domain.repository.economy;

import com.ssafy.buttonup.domain.model.entity.economy.EconomyTopic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 경제 주제 레포지토리
 *
 * @author Eunee Chung
 * created on 2022-02-14
 */

public interface EconomyTopicRepository extends JpaRepository<EconomyTopic, Long> {

    /**
     * 경제 주제 리스트 조회
     *
     * @return 경제 주제 리스트
     */

    List<EconomyTopic> findAll();
}