package com.ssafy.buttonup.domain.model.dto.invest.request;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 투자 현황 업데이트하는 DTO
 *
 * @author Jiun Kim
 * created on 2022-02-13
 */
@ApiModel(value = "투자 현황 업데이트 요청 정보")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvestStatusRequest {
    private long seq;
    private long investmentSeq;
    private long childSeq;
}
