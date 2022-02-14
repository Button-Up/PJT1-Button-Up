package com.ssafy.buttonup.domain.model.entity.economy;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * 경제 주제 엔티티
 *
 * @author eunee chung
 * created on 2022-02-11
 */

@Entity
@Table(name = "economy_topics")
@Getter
@DynamicInsert
@NoArgsConstructor
public class
EconomyTopic {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "economy_topic_seq")
    private Long seq;

    @Column(name="economy_topic_image")
    private String image;

    @Column(name="economy_topic_name")
    private String name;
}