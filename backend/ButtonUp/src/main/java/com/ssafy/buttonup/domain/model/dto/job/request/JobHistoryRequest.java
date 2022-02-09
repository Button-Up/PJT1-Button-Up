package com.ssafy.buttonup.domain.model.dto.job.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 직업 내역 추가 요청 DTO
 *
 * @author jiun kim
 * created on 2022-02-06
 */
@ApiModel(value = "직업 내역 추가 요청 정보")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobHistoryRequest {
    /* 직업 내역에 추가되는 직업 키 */
    @ApiModelProperty(value = "직업 키", required = true)
    private long jobSeq;

    /* 직업 내역 추가하는 자녀 키 */
    @ApiModelProperty(value = "자녀 키", required = true)
    private long childSeq;
}
