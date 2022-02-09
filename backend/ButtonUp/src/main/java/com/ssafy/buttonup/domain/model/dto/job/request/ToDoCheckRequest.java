package com.ssafy.buttonup.domain.model.dto.job.request;

/**
 * 아이 체크리스트 완료 여부 Dto
 *
 * @author SeungYeon Hwang
 * created on 2022-02-09
 */

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@ApiModel(value = "체크리스트 완료 여부")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ToDoCheckRequest {

    /* 아이 키 */
    @ApiModelProperty(value = "아이 키", required = true)
    private long childSeq;

    /* 체크리스트 키 */
    @ApiModelProperty(value = "체크리스트 키", required = true)
    private long checkListSeq;

    /* 완료 여부 */
    @ApiModelProperty(value = "체크리스트 완료 여부", required = true)
    private boolean flag;
}
