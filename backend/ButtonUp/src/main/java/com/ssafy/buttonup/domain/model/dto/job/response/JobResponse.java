package com.ssafy.buttonup.domain.model.dto.job.response;

import com.ssafy.buttonup.domain.model.entity.job.PayTerm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

/**
 * 직업 응답 DTO
 *
 * @author jiun kim
 * created on 2022-02-06
 */

@ApiModel(value = "직업 응답 정보")
@Getter
@Builder
public class JobResponse {
    /* 직업 키 */
    @ApiModelProperty(value = "직업 키", required = true)
    private final long seq;

    /* 급여 지급 주기 */
    @ApiModelProperty(value = "급여 지급 주기", required = true, example = "주급")
    private final PayTerm payTerm;

    /* 급여 */
    @ApiModelProperty(value = "급여", required = true)
    private final int pay;

    /* 직업명 */
    @ApiModelProperty(value = "직업명", required = true)
    private final String name;

    /* 직업 이미지 경로 */
    @ApiModelProperty(value = "직업 이미지 경로")
    private final String jobImagePath;
}
