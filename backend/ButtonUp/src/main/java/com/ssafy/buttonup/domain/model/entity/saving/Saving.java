package com.ssafy.buttonup.domain.model.entity.saving;


import com.ssafy.buttonup.domain.model.entity.user.Child;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * 적금 엔티티
 *
 * @author eungchol kim
 * created on 2022-02-11
 */


@Entity
@Table(name = "savings")
@Getter
@DynamicInsert
@NoArgsConstructor
public class Saving {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name="saving_seq")
    private Long seq;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "saving_start_date")
    private Date date;

    @Column(name = "saving_interest_rate")
    private Double interestRate;

    @Column(name = "saving_term")
    private int term;

    @Column(name="saving_transfer_amount")
    private int transferAmount;

    @Column(name="saving_state_type_flag")
    private boolean stateTypeFlag;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_child_seq")
    private Child child;

    @Builder
    public Saving(Long seq, Date date, Double interestRate, int term, int transferAmount, boolean stateTypeFlag, Child child) {
        this.seq = seq;
        this.date = date;
        this.interestRate = interestRate;
        this.term = term;
        this.transferAmount = transferAmount;
        this.stateTypeFlag = stateTypeFlag;
        this.child = child;
    }
}
