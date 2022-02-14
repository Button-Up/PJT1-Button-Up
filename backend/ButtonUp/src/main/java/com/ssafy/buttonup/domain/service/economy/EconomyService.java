package com.ssafy.buttonup.domain.service.economy;

import com.ssafy.buttonup.domain.model.entity.economy.EconomyTopic;
import com.ssafy.buttonup.domain.repository.economy.EconomyTopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 경제 공부방 서비스 클래스
 *
 * @author eunee chung
 * created on 2022-02-14
 */

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class EconomyService {
    private final EconomyTopicRepository economyTopicRepository;

    /**
     * 경제 공부방 주제 조회
     *
     * @return 경제 주제들 List<String>
     */

    public List<String> getEconomyTopic(){
        List<EconomyTopic> economyTopics = economyTopicRepository.findAll();
        List<String> responses = new ArrayList<>();


        for(EconomyTopic economyTopic : economyTopics){
            responses.add(economyTopic.getName());
        }
        return responses;
    }
}
