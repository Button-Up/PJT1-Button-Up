package com.ssafy.buttonup.domain.model.entity.stock;

import com.ssafy.buttonup.domain.model.entity.user.Parent;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "news_date")
    private Date date;

    @Column(name = "news_content")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_parent_seq")
    private Parent parent;
}