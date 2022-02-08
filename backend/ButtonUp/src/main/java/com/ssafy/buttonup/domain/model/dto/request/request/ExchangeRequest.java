package com.ssafy.buttonup.domain.model.dto.request.request;

import lombok.Builder;
import lombok.Getter;

/**
 * 환전 요청 내역 요청 dto
 *
 * @author jeongyeon woo
 * created on 2022-02-08
 */

@Getter
@Builder
public class ExchangeRequest {
    private long childSeq;
    private int price;
}
