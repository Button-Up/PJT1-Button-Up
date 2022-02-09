package com.ssafy.buttonup.domain.model.dto.account.request;

import com.ssafy.buttonup.domain.model.entity.account.AccountHistoryCategory;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 입출금 내역 추가하는 DTO
 *
 * @author jiun kim
 * created on 2022-02-03
 */
@ApiModel(value = "입출금 내역 추가 요청 정보")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HistoryRequest {
    /* 거래 이벤트 카테고리 */
    @ApiModelProperty(value = "거래 카테고리", required = true, example = "급여")
    private AccountHistoryCategory category;

    /* 거래 관련 이벤트 내용 */
    @ApiModelProperty(value = "이벤트 내용", required = true)
    private String content;

    /* 거래되는 금액 */
    @ApiModelProperty(value = "금액", required = true)
    private int money;

    /* 내역 추가하는 자녀 키 */
    @ApiModelProperty(value = "자녀 키", required = true)
    private long childSeq;
}
