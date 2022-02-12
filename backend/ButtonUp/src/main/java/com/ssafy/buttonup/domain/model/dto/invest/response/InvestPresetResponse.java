package com.ssafy.buttonup.domain.model.dto.invest.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

/**
 * 투자 프리셋 응답 DTO
 *
 * @author Jiun Kim
 * created on 2022-02-12
 */
@ApiModel(value = "투자 프리셋 응답 정보")
@Getter
@Builder
public class InvestPresetResponse {
    /* 프리셋 키 */
    @ApiModelProperty(value = "프리셋 키", required = true)
    private final long seq;

    /* 프리셋명 */
    @ApiModelProperty(value = "투자 분류명", required = true, example = "몸무게")
    private final String name;

    /* 프리셋 단위 */
    @ApiModelProperty(value = "투자 단위", required = true)
    private final String unit;
}
