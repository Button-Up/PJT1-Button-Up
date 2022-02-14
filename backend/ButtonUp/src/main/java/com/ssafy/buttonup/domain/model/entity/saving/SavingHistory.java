package com.ssafy.buttonup.domain.model.entity.saving;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.*;

/**
 *적금 내역 엔티티
 *
 * @author eungchol Kim
 * created on 2022-02-11
 */

@Entity
@Table(name = "saving_histories")
@Getter
@DynamicInsert
@NoArgsConstructor
public class SavingHistory {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "saving_history_seq")
    private Long seq;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_saving_seq")
    private Saving saving;

    @Column(name = "saving_history_money")
    private int money;

    @Column(name ="saving_history_balance")
    private int balance;

    @Column(name="saving_history_content")
    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="saving_history_date")
    private Date date;

    @Builder
    public SavingHistory(Long seq, Saving saving, int money, int balance, String content, Date date) {
        this.seq = seq;
        this.saving = saving;
        this.money = money;
        this.balance = balance;
        this.content = content;
        this.date = date;
    }
}
