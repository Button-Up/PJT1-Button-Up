package com.ssafy.buttonup.domain.model.dto.job.request;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

/**
 * 직업 내역 추가 요청 DTO
 *
 * @author jiun kim
 * created on 2022-02-06
 */
@Getter
@Builder
public class JobHistoryRequest implements Serializable {
    /* 직업 내역에 추가되는 직업 키 */
    private final long jobSeq;

    /* 직업 내역 추가하는 아이 키 */
    private final long childSeq;
}
