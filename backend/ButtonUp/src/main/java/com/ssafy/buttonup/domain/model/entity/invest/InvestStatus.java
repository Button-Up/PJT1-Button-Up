package com.ssafy.buttonup.domain.model.entity.invest;

import com.ssafy.buttonup.domain.model.entity.user.Child;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 투자 현황 엔티티
 *
 * @author Jiun Kim
 * created on 2022-02-11
 */
@Entity
@Table(name = "investment_statuses")
@Getter
@NoArgsConstructor
public class InvestStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "investment_status_seq")
    private Long seq;

    @Column(name = "investment_status_count")
    private int count;

    @Column(name = "investment_status_average_price")
    private double averagePrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_investment_seq")
    private Investment investment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_child_seq")
    private Child child;

    @Builder
    public InvestStatus(int count, double averagePrice, Investment investment, Child child) {
        this.count = count;
        this.averagePrice = averagePrice;
        this.investment = investment;
        this.child = child;
    }
}