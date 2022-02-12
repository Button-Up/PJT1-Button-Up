package com.ssafy.buttonup.domain.model.dto.invest.response;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;

/**
 * 주식 목록 응답 DTO
 *
 * @author Jiun Kim
 * created on 2022-02-12
 */
@ApiModel(value = "주식 프리셋 응답 정보")
@Getter
@Builder
public class StockResponse {
    private final long seq;
    private final String target;
}
