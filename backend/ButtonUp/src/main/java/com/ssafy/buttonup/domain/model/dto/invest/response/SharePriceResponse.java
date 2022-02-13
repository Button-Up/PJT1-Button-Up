package com.ssafy.buttonup.domain.model.dto.invest.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

/**
 * 투자 종목 시세 응답 DTO
 *
 * @author Jiun Kim
 * created on 2022-02-13
 */
@ApiModel(value = "투자 종목 시세 응답 정보")
@Getter
@Builder
public class SharePriceResponse {
    /* 날짜 */
    @ApiModelProperty(value = "날짜", required = true)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private final Date date;

    /* 시세 */
    @ApiModelProperty(value = "시세", required = true)
    private final int price;
}
