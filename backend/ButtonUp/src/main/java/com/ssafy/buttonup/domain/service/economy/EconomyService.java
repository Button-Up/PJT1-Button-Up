package com.ssafy.buttonup.domain.service.economy;

import com.ssafy.buttonup.domain.model.dto.economy.response.PostResponse;
import com.ssafy.buttonup.domain.model.entity.economy.EconomyPost;
import com.ssafy.buttonup.domain.model.entity.economy.EconomyTopic;
import com.ssafy.buttonup.domain.repository.economy.EconomyPostRepository;
import com.ssafy.buttonup.domain.repository.economy.EconomyTopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
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
    private final EconomyPostRepository economyPostRepository;

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
        Collections.sort(responses);
        return responses;
    }

    /**
     * 경제 공부방 주제별 내용 조회
     *
     * @param topicSeq 경제 주제 이름
     * @return 경제 내용 List<String>
     */

    public List<PostResponse> getEconomyPost(long topicSeq){
        List<EconomyPost> economyPosts = economyPostRepository.findByTopic_SeqOrderByOrder(topicSeq);
        List<PostResponse> responses = new ArrayList<>();
        for(EconomyPost e: economyPosts){
            responses.add(e.toResponse());
        }
        return responses;
    }
}
