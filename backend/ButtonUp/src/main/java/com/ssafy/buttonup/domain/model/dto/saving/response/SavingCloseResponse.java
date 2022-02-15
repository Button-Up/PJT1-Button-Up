package com.ssafy.buttonup.domain.model.dto.saving.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

/**
 * 적금 해지 응답 DTO
 *
 * @author eungchol kim
 * created on 2022-02-14
 */

@ApiModel(value = "적금 해지 시 응답 정보")
@Getter
@Builder
public class SavingCloseResponse {
    /* 해지 시 반환금 정보 */
    @ApiModelProperty(value = "현재까지 적금한 금액", required = true)
    private int money;
}
