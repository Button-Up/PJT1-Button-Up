package com.ssafy.buttonup.domain.model.entity.invest;

import com.ssafy.buttonup.domain.model.dto.invest.response.NewsResponse;
import com.ssafy.buttonup.domain.model.entity.user.Parent;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

/**
 * 뉴스 엔티티
 *
 * @author Jiun Kim
 * created on 2022-02-11
 */
@Entity
@Table(name = "news")
@Getter
@NoArgsConstructor
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "news_seq")
    private Long seq;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "news_date")
    private Date date;

    @Column(name = "news_content")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_parent_seq")
    private Parent parent;

    @Builder
    public News(String content, Parent parent) {
        this.content = content;
        this.parent = parent;
    }

    /**
     * News Entity를 NewsResponse dto로 바꿔주는 메서드
     *
     * @return NewsResponse
     */
    public NewsResponse toResponse() {
        return NewsResponse.builder()
                .date(date)
                .content(content)
                .build();
    }
}