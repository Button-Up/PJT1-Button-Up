package com.ssafy.buttonup.domain.model.dto.saving.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 적금통장 개설하는 DTO
 *
 * @author eungchol kim
 * created on 2022-02-12
 */

@ApiModel(value = "적금 신청, 조회 정보")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SavingRequest {
    /* 적금 이체 금액 */
    @ApiModelProperty(value = "이체 금액", required = true)
    private int transferAmount;

    /* 적금하는 자녀 키 */
    @ApiModelProperty(value = "자녀 키", required = true)
    private  long childSeq;
}
