package com.ssafy.buttonup.domain.model.dto.job.response;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@ApiModel(value = "체크리스트 응답 정보")
@Getter
@Builder
public class ToDoCheckResponse implements Serializable {

    /* 체크리스트 키 */
    @ApiModelProperty(value = "체크리스트 키",required = true)
    private final long seq;

    /* 할일 내용 */
    @ApiModelProperty(value = "할일 내용",required = true)
    private final String content;

    /* 할일 완료 여부 */
    @ApiModelProperty(value = "할일 완료 여부",required = true)
    private final boolean flag;
}
