package com.ssafy.buttonup.domain.model.dto.job.request;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

/**
 * 직업 요청 DTO
 *
 * @author jiun kim
 * created on 2022-02-05
 */
@Getter
@Builder
public class JobRequest implements Serializable {
    /* 급여 지급 주기 */
    private final int payTerm;

    /* 급여 */
    private final int pay;

    /* 직업명 */
    private final String name;

    /* 이미지 키 */
    private final long jobImageSeq;
    
    /* 부모 키 */
    private final long parentSeq;
}
