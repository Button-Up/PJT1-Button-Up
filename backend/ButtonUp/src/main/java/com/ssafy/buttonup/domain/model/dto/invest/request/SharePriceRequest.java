package com.ssafy.buttonup.domain.model.dto.invest.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 시세 추가하는 DTO
 *
 * @author Jiun Kim
 * created on 2022-02-14
 */
@ApiModel(value = "시세 추가 요청 정보")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SharePriceRequest {
    /* 투자 키 */
    @ApiModelProperty(value = "투자 종목 키", required = true)
    private long investSeq;

    /* 가격 */
    @ApiModelProperty(value = "가격", required = true)
    private int price;
}
