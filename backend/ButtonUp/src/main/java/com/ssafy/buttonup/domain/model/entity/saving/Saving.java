package com.ssafy.buttonup.domain.model.entity.saving;


import com.ssafy.buttonup.domain.model.dto.saving.response.SavingDetailResponse;
import com.ssafy.buttonup.domain.model.dto.saving.response.SavingHistoryResponse;
import com.ssafy.buttonup.domain.model.entity.user.Child;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

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

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "saving_start_date")
    private Date date;

    @Column(name = "saving_interest_rate")
    private double interestRate;

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
    public Saving(Double interestRate, int term, int transferAmount, boolean stateTypeFlag, Child child) {
        this.interestRate = interestRate;
        this.term = term;
        this.transferAmount = transferAmount;
        this.stateTypeFlag = stateTypeFlag;
        this.child = child;
    }

    public SavingDetailResponse toDetailResponse(List<SavingHistoryResponse> histories, double interestRate){
        LocalDate nowDate = LocalDate.now();
        LocalDate endDate = date.toInstant() // Date -> Instant
                .atZone(ZoneId.systemDefault()) // Instant -> ZonedDateTime
                .toLocalDate().plusDays(99);
        int restDate = Math.toIntExact(ChronoUnit.DAYS.between(nowDate, endDate));

        return SavingDetailResponse.builder()
                .savingInterestRate(interestRate * 100)
                .money(histories.get(0).getMoney())
                .restDate(restDate)
                .createdDate(date)
                .nowDate(Date.from(nowDate.atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .endDate(Date.from(endDate.atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .presentInterest((int)(histories.get(0).getMoney()*interestRate))
                .maxMoney(50000)
                .histories(histories)
                .build();
    }

    public void changeState(boolean stateTypeFlag){
        this.stateTypeFlag = stateTypeFlag;
    }
}
