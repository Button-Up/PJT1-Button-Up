package com.ssafy.buttonup.domain.model.dto.request.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 환전 요청 내역 요청 dto
 *
 * @author jeongyeon woo
 * created on 2022-02-08
 */

@ApiModel(value = "환전 요청 정보")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeRequest {
    
    @ApiModelProperty(value="아이 키", required = true)
    private long childSeq;

    @ApiModelProperty(value="환전 금액", required = true)
    private int price;
}
