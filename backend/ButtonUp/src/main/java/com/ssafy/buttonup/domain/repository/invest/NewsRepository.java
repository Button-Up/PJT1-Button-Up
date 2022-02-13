package com.ssafy.buttonup.domain.repository.invest;

import com.ssafy.buttonup.domain.model.entity.invest.News;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 투자 뉴스 레포지토리
 *
 * @author Jiun Kim
 * created on 2022-02-14
 */
public interface NewsRepository extends JpaRepository<News, Long> {
    /**
     * 부모 키로 모든 뉴스 검색
     *
     * @param parentSeq 부모 키
     * @return 뉴스 목록
     */
    List<News> findByParent_SeqOrderByDateDesc(long parentSeq);
}