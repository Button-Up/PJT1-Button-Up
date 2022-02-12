package com.ssafy.buttonup.domain.model.entity.invest;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

/**
 * 투자 주가 엔티티
 *
 * @author Jiun Kim
 * created on 2022-02-11
 */
@Entity
@Table(name = "share_prices")
@Getter
@NoArgsConstructor
public class SharePrice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "share_price_seq")
    private Long seq;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "share_price_date")
    private Date date;

    @Column(name = "share_price_price")
    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_investment_seq")
    private Investment investment;

    @Builder
    public SharePrice(int price, Investment investment) {
        this.price = price;
        this.investment = investment;
    }
}