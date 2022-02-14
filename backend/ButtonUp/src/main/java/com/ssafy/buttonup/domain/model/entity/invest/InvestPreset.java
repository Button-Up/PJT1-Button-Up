package com.ssafy.buttonup.domain.model.entity.invest;

import com.ssafy.buttonup.domain.model.dto.invest.response.InvestPresetResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 투자 프리셋 엔티티
 *
 * @author Jiun Kim
 * created on 2022-02-11
 */
@Entity
@Table(name = "investment_presets")
@Getter
@NoArgsConstructor
public class InvestPreset {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "investment_preset_seq")
    private Long seq;

    @Column(name = "investment_preset_name")
    private String name;

    @Column(name = "investment_preset_unit")
    private String unit;

    /**
     * InvestPreset Entity를 Response dto로 바꿔주는 메서드
     *
     * @return InvestPresetResponse
     */
    public InvestPresetResponse toResponse() {
        return InvestPresetResponse.builder()
                .seq(this.seq)
                .name(this.name)
                .unit(this.unit)
                .build();
    }
}
