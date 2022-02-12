package com.ssafy.buttonup.domain.model.entity.invest;

import com.ssafy.buttonup.domain.model.dto.invest.response.RoughInvestResponse;
import com.ssafy.buttonup.domain.model.entity.user.Parent;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

/**
 * 투자 엔티티
 *
 * @author Jiun Kim
 * created on 2022-02-11
 */
@Entity
@Table(name = "investments")
@Getter
@NoArgsConstructor
public class Investment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "investment_seq")
    private Long seq;

    @Column(name = "investment_target")
    private String target;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "investment_start_date")
    private Date startDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_investment_preset_seq")
    private InvestPreset investPreset;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_parent_seq")
    private Parent parent;

    @Builder
    public Investment(String target, InvestPreset investPreset, Parent parent) {
        this.target = target;
        this.investPreset = investPreset;
        this.parent = parent;
    }

    /**
     * Invest Entity를 RoughInvestResponse Dto로 번환
     *
     * @return RoughInvestResponse
     */
    public RoughInvestResponse toRoughInvestResponse() {
        return RoughInvestResponse.builder()
                .seq(this.seq)
                .name(this.target + " " + investPreset.getName())
                .build();
    }
}