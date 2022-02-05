package com.ssafy.buttonup.domain.model.dto.job.response;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Getter
@Builder
public class JobResponse implements Serializable {
    /* 직업 키 */
    private final Long seq;

    /* 급여 지급 주기 */
    private final int payTerm;

    /* 급여 */
    private final int pay;

    /* 직업명 */
    private final String name;

    /* 직업 이미지 경로 */
    private final String jobImagePath;
}
