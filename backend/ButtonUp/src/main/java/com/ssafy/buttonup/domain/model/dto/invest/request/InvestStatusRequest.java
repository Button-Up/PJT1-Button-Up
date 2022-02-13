package com.ssafy.buttonup.domain.model.dto.invest.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 투자 현황 업데이트하는 DTO
 *
 * @author Jiun Kim
 * created on 2022-02-13
 */
@ApiModel(value = "투자 현황 업데이트 요청 정보")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvestStatusRequest {
    /* 투자 현황 키 */
    @ApiModelProperty(value = "투자 현황 키", required = true)
    private long seq;

    /* 매도/매수 개수 */
    @ApiModelProperty(value = "매도/매수 개수", required = true)
    private int count;

    /* 매수 시 가격 */
    @ApiModelProperty(value = "매수/매도 시 가격", required = true)
    private int price;
}
