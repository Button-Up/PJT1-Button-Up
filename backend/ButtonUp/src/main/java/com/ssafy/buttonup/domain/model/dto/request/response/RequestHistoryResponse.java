package com.ssafy.buttonup.domain.model.dto.request.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ssafy.buttonup.domain.model.entity.request.RequestHistoryStatus;
import com.ssafy.buttonup.domain.model.entity.request.RequestHistoryType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * 요청 내역 응답 dto
 *
 * @author jeongyeon woo
 * created on 2022-02-08
 */

@ApiModel(value = "요청 내역 응답 정보")
@Getter
@Builder
public class RequestHistoryResponse {
    @ApiModelProperty(value="환전 요청 키", required = true)
    private Long seq;

    @ApiModelProperty(value="요청 타입(소원권, 상품, 환전)", required = true)
    private RequestHistoryType type;

    @ApiModelProperty(value="날짜", required = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date date;

    @ApiModelProperty(value="요청 상태(미완료, 거절, 승인)", required = true)
    private RequestHistoryStatus status;

    @ApiModelProperty(value="금액", required = true)
    private int price;
}
