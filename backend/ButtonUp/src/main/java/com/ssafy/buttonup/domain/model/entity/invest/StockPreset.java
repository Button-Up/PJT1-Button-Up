package com.ssafy.buttonup.domain.model.entity.invest;

import com.ssafy.buttonup.domain.model.dto.invest.response.StockPresetResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 주식 프리셋 엔티티
 *
 * @author Jiun Kim
 * created on 2022-02-11
 */
@Entity
@Table(name = "stock_presets")
@Getter
@NoArgsConstructor
public class StockPreset {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stock_preset_seq")
    private Long seq;

    @Column(name = "stock_preset_name")
    private String name;

    @Column(name = "stock_preset_unit")
    private String unit;

    /**
     * StockPreset Entity를 Response dto로 바꿔주는 메서드
     *
     * @return StockPresetResponse
     */
    public StockPresetResponse toResponse() {
        return StockPresetResponse.builder()
                .seq(this.seq)
                .name(this.name)
                .unit(this.unit)
                .build();
    }
}
