package com.ssafy.buttonup.domain.service.invest;

import com.ssafy.buttonup.domain.model.dto.invest.request.NewsRequest;
import com.ssafy.buttonup.domain.model.dto.invest.response.NewsResponse;
import com.ssafy.buttonup.domain.model.entity.invest.News;
import com.ssafy.buttonup.domain.repository.invest.NewsRepository;
import com.ssafy.buttonup.domain.repository.user.ParentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 투자 뉴스 관련된 서비스
 *
 * @author Jiun Kim
 * created on 2022-02-14
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class NewsService {
    private final NewsRepository newsRepository;
    private final ParentRepository parentRepository;

    /**
     * 모든 뉴스 목록 반환
     *
     * @param parentSeq 부모 키
     * @return 뉴스 목록
     */
    public List<NewsResponse> getAllNewsListByParent(long parentSeq) {
        List<News> newsList = newsRepository.findByParent_SeqOrderByDateDesc(parentSeq);
        List<NewsResponse> responses = new ArrayList<>();
        for (News news :
                newsList) {
            responses.add(news.toResponse());
        }
        return responses;
    }

    /**
     * 뉴스 등록
     *
     * @param request 뉴스 정보
     */
    public void insertNews(NewsRequest request) {
        newsRepository.save(News.builder()
                .content(request.getContent())
                .parent(parentRepository.getById(request.getParentSeq()))
                .build());
    }
}
