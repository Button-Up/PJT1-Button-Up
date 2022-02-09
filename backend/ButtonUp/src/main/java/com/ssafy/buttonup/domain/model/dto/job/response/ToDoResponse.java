package com.ssafy.buttonup.domain.model.dto.job.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

/**
 * 할일 응답 DTO
 *
 * @author SeungYeon Hwang
 * created on 2022-02-07
 */

@ApiModel(value = "할일 응답 정보")
@Getter
@Builder
public class ToDoResponse {

    /* 할일 키 */
    @ApiModelProperty(value = "할일 키",required = true)
    private final long seq;

    /* 할일 내용 */
    @ApiModelProperty(value = "할일 내용",required = true)
    private final String content;
}
