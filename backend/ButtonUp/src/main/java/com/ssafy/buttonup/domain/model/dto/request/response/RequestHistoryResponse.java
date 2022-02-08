package com.ssafy.buttonup.domain.model.dto.request.response;

import com.ssafy.buttonup.domain.model.entity.request.RequestHistoryStatus;
import com.ssafy.buttonup.domain.model.entity.request.RequestHistoryType;
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

@Getter
@Builder
public class RequestHistoryResponse {
    private Long seq;
    private RequestHistoryType type;
    private Date date;
    private RequestHistoryStatus status;
    private int price;
}
