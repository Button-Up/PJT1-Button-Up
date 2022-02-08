package com.ssafy.buttonup.domain.model.dto.user.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

/**
 * 튜토리얼 현재 단계 Dto
 *
 * @author SeungYeon Hwang
 * created on 2022-02-09
 */

@ApiModel(value = "튜토리얼 요청 정보")
@Getter
@Builder
public class TutorialRequest {

    /* 부모 키 */
    @ApiModelProperty(value = "부모 키", required = true)
    private long parentSeq;

    /* 현재 단계 */
    @ApiModelProperty(value = "현재 단계", required = true)
    private int stage;

}
