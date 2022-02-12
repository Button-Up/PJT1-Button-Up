package com.ssafy.buttonup.domain.model.entity.economy;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * 퀴즈 엔티티
 *
 * @author eunee chung
 * created on 2022-02-11
 */


@Entity
@Table(name = "quizzes")
@Getter
@DynamicInsert
@NoArgsConstructor
public class Quiz {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name="quiz_seq")
    private Long seq;

    @Column(name="quiz_content")
    private String content;

    @Column(name="quiz_answer")
    private String answer;

    @Enumerated(EnumType.STRING)
    @Column(name="quiz_type")
    private QuizType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_economy_topic_seq")
    private EconomyTopic topic;
}