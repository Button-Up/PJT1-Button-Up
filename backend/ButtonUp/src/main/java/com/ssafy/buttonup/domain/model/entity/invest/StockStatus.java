package com.ssafy.buttonup.domain.model.entity.invest;

import com.ssafy.buttonup.domain.model.entity.user.Child;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 주식 현황 엔티티
 *
 * @author Jiun Kim
 * created on 2022-02-11
 */
@Entity
@Table(name = "stock_statuses")
@Getter
@NoArgsConstructor
public class StockStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stock_status_seq")
    private Long seq;

    @Column(name = "stock_status_count")
    private int count;

    @Column(name = "stock_status_average_price")
    private double averagePrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_stock_seq")
    private Stock stock;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_child_seq")
    private Child child;

    @Builder
    public StockStatus(int count, double averagePrice, Stock stock, Child child) {
        this.count = count;
        this.averagePrice = averagePrice;
        this.stock = stock;
        this.child = child;
    }
}