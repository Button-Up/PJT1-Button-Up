package com.ssafy.buttonup.domain.model.dto.request.request;

import com.ssafy.buttonup.domain.model.entity.user.Child;
import com.ssafy.buttonup.domain.model.entity.product.Product;
import com.ssafy.buttonup.domain.model.entity.request.RequestHistoryStatus;
import com.ssafy.buttonup.domain.model.entity.request.RequestHistoryType;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class RequestHistoryRequest {
    private Long seq;
    private RequestHistoryType type;
    private Date date;
    private RequestHistoryStatus status;
    private int price;
    private Product product;
    private Child child;
}
