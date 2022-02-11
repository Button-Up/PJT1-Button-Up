package com.ssafy.buttonup.domain.model.entity.stock;

import com.ssafy.buttonup.domain.model.entity.user.Parent;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * 주식 엔티티
 *
 * @author Jiun Kim
 * created on 2022-02-11
 */
@Entity
@Table(name = "stocks")
@Getter
@NoArgsConstructor
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stock_seq")
    private Long seq;

    @Column(name = "stock_target")
    private String target;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "stock_start_date")
    private Date startDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_stock_preset_seq")
    private StockPreset stockPreset;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_parent_seq")
    private Parent parent;
}