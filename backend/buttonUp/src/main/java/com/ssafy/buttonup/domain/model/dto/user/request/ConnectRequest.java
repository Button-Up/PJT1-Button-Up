package com.ssafy.buttonup.domain.model.dto.user.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 아이와 부모 연결 Request DTO
 *
 * @author jeongyeou woo
 * created on 2022-02-03
 */
@Getter @Setter
@AllArgsConstructor
public class ConnectRequest {
    private long parentSeq;
    private long childSeq;
}
