package com.ssafy.buttonup.domain.model.dto.economy.response;

import com.ssafy.buttonup.domain.model.dto.economy.Quiz;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * 경제 주제별 퀴즈 조회 응답 DTO
 *
 * @author Eunee Chung
 * created on 2022-02-14
 */

@ApiModel(value = "경제 주제별 퀴즈 조회 응답 DTO")
@Getter
@Builder
@ToString
public class QuizResponse {

    @ApiModelProperty(value = "경제 주제별 퀴즈 리스트")
    @Setter
    private List<Quiz> quizList;
}
