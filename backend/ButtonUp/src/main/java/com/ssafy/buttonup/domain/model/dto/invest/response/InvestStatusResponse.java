package com.ssafy.buttonup.domain.model.dto.invest.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 투자 종목별 현황 조회 응답 DTO
 *
 * @author Jiun Kim
 * created on 2022-02-13
 */
@ApiModel(value = "투자 종목별 응답 정보")
@Getter
@Builder
public class InvestStatusResponse {
    /* 종목 키 */
    @ApiModelProperty(value = "종목 키", required = true)
    private final long seq;

    /* 종목명 */
    @ApiModelProperty(value = "종목명", required = true)
    private final String name;

    /* 종목 단위 */
    @ApiModelProperty(value = "종목 단위", required = true)
    private final String unit;

    /* 종목 보유 개수 */
    @ApiModelProperty(value = "종목 보유 개수", required = true)
    private final int count;

    /* 종목 평균 가격 */
    @ApiModelProperty(value = "종목 평균 가격", required = true)
    private final double averagePrice;

    /* 종목 주가 변화 데이터*/
//    @Setter
    @ApiModelProperty(value = "종목 주가 변화", required = true)
    private final List<SharePriceResponse> prices;
}
