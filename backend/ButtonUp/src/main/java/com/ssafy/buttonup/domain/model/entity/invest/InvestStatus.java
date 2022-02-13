package com.ssafy.buttonup.domain.model.entity.invest;

import com.ssafy.buttonup.domain.model.dto.invest.response.InvestStatusResponse;
import com.ssafy.buttonup.domain.model.dto.invest.response.SharePriceResponse;
import com.ssafy.buttonup.domain.model.entity.user.Child;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

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
    
    /**
     * InvestStatus Entity를 InvestStatusResponse Dto로 번환
     *
     * @return InvestStatusResponse
     */
    public InvestStatusResponse toInvestStatusResponse(List<SharePriceResponse> prices) {
        return InvestStatusResponse.builder()
                .seq(seq)
                .name(investment.getTarget() + " " + investment.getInvestPreset().getName())
                .unit(investment.getInvestPreset().getUnit())
                .count(count)
                .averagePrice(averagePrice)
                .prices(prices)
                .build();
    }
}