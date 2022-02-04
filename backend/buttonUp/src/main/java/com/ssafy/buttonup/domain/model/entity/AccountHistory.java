package com.ssafy.buttonup.domain.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * 입출금 내역 엔티티
 *
 * @author jiun kim
 * created on 2022-02-02
 */
@Entity
@Table(name = "account_histories")
@Getter
@Builder
@DynamicInsert
@AllArgsConstructor
@NoArgsConstructor
public class AccountHistory {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "account_history_seq")
    private Long seq;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_history_type")
    private AccountHistoryType type;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_history_category")
    private AccountHistoryCategory category;

    @Column(name = "account_history_content")
    private String content;

    @Column(name = "account_history_money")
    private int money;

    @Column(name = "account_history_balance")
    private int balance;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "account_history_date")
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_child_seq")
    private Child child;
}