package com.ssafy.buttonup.domain.model.entity.invest;

import com.ssafy.buttonup.domain.model.dto.invest.response.RoughStockResponse;
import com.ssafy.buttonup.domain.model.entity.user.Parent;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

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

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "stock_start_date")
    private Date startDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_stock_preset_seq")
    private StockPreset stockPreset;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_parent_seq")
    private Parent parent;

    @Builder
    public Stock(String target, StockPreset stockPreset, Parent parent) {
        this.target = target;
        this.stockPreset = stockPreset;
        this.parent = parent;
    }

    /**
     * Stock Entity를 RoughStockResponse Dto로 번환
     *
     * @return RoughStockResponse
     */
    public RoughStockResponse toRoughStockResponse() {
        return RoughStockResponse.builder()
                .seq(this.seq)
                .name(this.target + " " + stockPreset.getName())
                .build();
    }
}