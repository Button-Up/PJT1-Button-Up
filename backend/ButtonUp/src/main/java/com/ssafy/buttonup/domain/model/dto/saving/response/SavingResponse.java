package com.ssafy.buttonup.domain.model.dto.saving.response;

import com.ssafy.buttonup.domain.model.entity.account.AccountHistoryCategory;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

/**
 * 적금통장 기본정보 보여주는 DTO
 *
 * @author eungchol kim
 * created on 2022-02-12
 */

@ApiModel(value = "적금 내역 조회 응답 정보")
@Getter
@Builder
public class SavingResponse {

    /* 거래 이벤트 카테고리 */
    @ApiModelProperty(value = "거래 카테고리", required = true, example = "적금")
    private final AccountHistoryCategory category;

    /* 금리 */
    @ApiModelProperty(value = "적금 금리", required = true)
    private final double interestRate;


}
