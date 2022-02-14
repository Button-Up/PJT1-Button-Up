package com.ssafy.buttonup.domain.model.entity.economy;

import com.ssafy.buttonup.domain.model.dto.economy.response.PostResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * 경제 사전 엔티티
 *
 * @author eunee chung
 * created on 2022-02-11
 */

@Entity
@Table(name = "economy_posts")
@Getter
@DynamicInsert
@NoArgsConstructor
public class EconomyPost {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name= "economy_post_seq")
    private Long seq;

    @Column(name="economy_post_title")
    private String title;

    @Column(name="economy_post_image")
    private String image;

    @Column(name="economy_post_content")
    private String content;

    @Column(name="economy_post_order")
    protected int order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="fk_economy_topic_seq")
    private EconomyTopic topic;

    /**
     * EconomyPost Entity를 PostResponse Dto로 번환
     *
     * @return PostResponse
     */
    public PostResponse toResponse(){
       return PostResponse.builder()
                .seq(seq)
                .title(title)
                .image(image)
                .content(content)
                .order(order)
               .build();
    }
}