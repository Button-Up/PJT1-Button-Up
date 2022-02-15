package com.ssafy.buttonup.domain.model.dto.saving.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;
import java.util.List;

/**
 * 적금 내역 상세 조회 응답 DTO
 *
 * @author eungchol kim
 * created on 2022-02-14
 */

@ApiModel(value = "적금 내역 상세 응답 정보")
@Getter
@Builder
public class SavingDetailResponse {
    /* 금리 */
    @ApiModelProperty(value = "적금 금리",required = true)
    private double savingInterestRate;

    /* 현재까지 적금한 금액 */
    @ApiModelProperty(value = "현재까지 적금한 금액", required = true)
    private int money;

    /* 남은 날짜 */
    private int restDate;

    /* 적금 시작 날짜 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createdDate;

    /* 현재 날짜 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date nowDate;

    /* 끝나는 날짜 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    /* 지금까지 쌓인 이자 */
    private int presentInterest;

    /* 최대로 모을 수 있는 금액 */
    private  int maxMoney;

    /* 적금 내역 */
    private List<SavingHistoryResponse> histories;

}
