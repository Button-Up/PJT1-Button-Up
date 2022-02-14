package com.ssafy.buttonup.domain.model.dto.account.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ssafy.buttonup.domain.model.entity.account.AccountHistoryCategory;
import com.ssafy.buttonup.domain.model.entity.account.AccountHistoryType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

/**
 * 입출금 내역 조회 응답 DTO
 *
 * @author jiun kim
 * created on 2022-02-04
 */
@ApiModel(value = "입출금 내역 조회 응답 정보")
@Getter
@Builder
public class HistoryResponse {
    /* 거래 입출금 구분 */
    @ApiModelProperty(value = "입출금 구분", required = true, example = "입금")
    private final AccountHistoryType type;
    
    /* 거래 이벤트 카테고리 */
    @ApiModelProperty(value = "거래 카테고리", required = true, example = "급여")
    private final AccountHistoryCategory category;

    /* 거래 관련 이벤트 내용 */
    @ApiModelProperty(value = "이벤트 내용", required = true)
    private final String content;

    /* 거래되는 금액 */
    @ApiModelProperty(value = "금액", required = true)
    private final int money;

    /* 거래 후 잔액 */
    @ApiModelProperty(value = "잔액", required = true)
    private final int balance;
    
    /* 거래 일시 */
    @ApiModelProperty(value = "거래 일시", required = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final Date date;
}
