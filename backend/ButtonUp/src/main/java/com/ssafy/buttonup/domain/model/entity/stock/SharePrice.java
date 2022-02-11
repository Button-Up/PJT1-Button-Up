package com.ssafy.buttonup.domain.model.entity.stock;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * 주식 주가 엔티티
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

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "share_price_date")
    private Date date;

    @Column(name = "share_price_price")
    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_stock_seq")
    private Stock stock;
}