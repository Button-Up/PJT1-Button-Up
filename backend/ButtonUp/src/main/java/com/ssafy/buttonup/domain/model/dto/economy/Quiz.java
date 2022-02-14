package com.ssafy.buttonup.domain.model.dto.economy;

import com.ssafy.buttonup.domain.model.entity.economy.QuizType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

/**
 * 퀴즈 DTO
 *
 * @author eunee chung
 * created on 2022-02-14
 */
@ApiModel(value = "퀴즈 정보")
@Getter
@Builder
public class Quiz {

    @ApiModelProperty(value = "퀴즈 키", required = true)
    private long seq;

    @ApiModelProperty(value = "퀴즈 제목", required = true)
    private String title;

    @ApiModelProperty(value = "퀴즈 내용", required = true)
    private String content;

    @ApiModelProperty(value = "퀴즈 순서", required = true)
    private int order;

    @ApiModelProperty(value = "퀴즈 타입",required = true)
    private QuizType type;

}
