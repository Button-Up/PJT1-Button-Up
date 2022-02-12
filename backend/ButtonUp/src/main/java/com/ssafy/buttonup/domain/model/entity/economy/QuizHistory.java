package com.ssafy.buttonup.domain.model.entity.economy;

import com.ssafy.buttonup.domain.model.entity.user.Child;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * 퀴즈 내역 엔티티
 *
 * @author eunee chung
 * created on 2022-02-11
 */

@Entity
@Table(name = "quiz_histories")
@Getter
@DynamicInsert
@NoArgsConstructor
public class QuizHistory {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name="quiz_history_seq")
    private Long seq;

    @Column(name="quiz_history_correct_number")
    private int correctNumber;

    @Column(name="quiz_history_pass_flag")
    private boolean pass;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="fk_child_seq")
    private Child child;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="fk_economy_topic_seq")
    private EconomyTopic topic;
}