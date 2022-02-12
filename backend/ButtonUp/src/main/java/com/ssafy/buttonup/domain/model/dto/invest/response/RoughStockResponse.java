package com.ssafy.buttonup.domain.model.dto.invest.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

/**
 * 투자 목록 응답 DTO
 *
 * @author Jiun Kim
 * created on 2022-02-12
 */
@ApiModel(value = "투자 목록 응답 정보")
@Getter
@Builder
public class RoughStockResponse {
    /* 주식 키 */
    @ApiModelProperty(value = "주식 키", required = true)
    private final long seq;

    /* 투자명 = 타켓명 + 프리셋명 ex) 엄마 몸무게 */
    @ApiModelProperty(value = "투자명", required = true)
    private final String name;
}
