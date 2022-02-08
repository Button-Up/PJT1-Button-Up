package com.ssafy.buttonup.domain.model.dto.user.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

/**
 * 튜토리얼 응답 Dto
 *
 * @author SeungYeon Hwang
 * created on 2022-02-09
 */

@ApiModel(value = "튜토리얼 응답 정보")
@Getter
@Builder
public class TutorialResponse {

    /* 현재 단계 */
    @ApiModelProperty(value = "현재 단계", required = true)
    private int stage;
}
