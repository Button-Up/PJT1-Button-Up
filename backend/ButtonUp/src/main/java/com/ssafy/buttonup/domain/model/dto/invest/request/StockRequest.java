package com.ssafy.buttonup.domain.model.dto.invest.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 투자 항목 추가하는 DTO
 *
 * @author Jiun Kim
 * created on 2022-02-12
 */
@ApiModel(value = "투자 항목 추가 관련 요청 정보")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StockRequest {
    /* 투자 항목의 대상 */
    @ApiModelProperty(value = "투자 항목 대상 이름", required = true, example = "어머니")
    private String target;

    /* 시작 가격 */
    @ApiModelProperty(value = "투자 항목 대상 이름", required = true)
    private int price;

    /* 투자 프리셋 키 */
    @ApiModelProperty(value = "투자 프리셋 키", required = true)
    private long stockPresetSeq;

    /* 부모 키 */
    @ApiModelProperty(value = "부모 키", required = true)
    private long parentSeq;
}
