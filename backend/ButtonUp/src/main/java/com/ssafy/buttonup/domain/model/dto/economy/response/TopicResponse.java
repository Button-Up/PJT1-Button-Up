package com.ssafy.buttonup.domain.model.dto.economy.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 경제 주제 조회 응답 DTO
 *
 * @author eunee chung
 * created on 2022-02-14
 */

@ApiModel(value = "경제 주제 응답 DTO")
@Getter
@Builder
public class TopicResponse {

    @ApiModelProperty(value = "주제 리스트", required = true)
    @Setter

    private List<String> topics;
}
