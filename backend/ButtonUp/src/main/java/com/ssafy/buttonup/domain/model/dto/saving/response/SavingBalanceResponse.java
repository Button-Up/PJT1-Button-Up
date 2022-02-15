package com.ssafy.buttonup.domain.model.dto.saving.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

/**
 * 적금 통장 잔액 조회 응답 DTO
 *
 * @author eungchol kim
 * created on 2022-02-12
 */

@ApiModel(value = "적금 잔액 응답 정보")
@Getter
@Builder
public class SavingBalanceResponse {
    /* 적금 상태*/
    @ApiModelProperty(value = "적금 들었는지 안들었는지 여부",required = true)
    private boolean StateType;

    /* 적금 잔액 */
    @ApiModelProperty(value = "적금 잔액", required = true)
    private int balance;

}
