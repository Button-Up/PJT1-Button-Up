package com.ssafy.buttonup.domain.model.dto.economy.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

/**
 * 경제 주제별 내용 응답 DTO
 *
 * @author Eunee Chung
 * created on 2022-02-14
 */

@ApiModel(value = "주제별 경제 사전 상세 페이지 응답 정보")
@Getter
@Builder
public class PostResponse {
    
    @ApiModelProperty(value = "경제 사전 상세 페이지 키 ",    required = true)
    private  Long seq;

    @ApiModelProperty(value = "경제 사전 상세 페이지 제목", required = true)
    private  String title;

    @ApiModelProperty(value = "경제 사전 상세 페이지 이미지", required = true)
    private  String image;

    @ApiModelProperty(value = "경제 사전 상세 페이지 내용", required = true)
    private  String content;

    @ApiModelProperty(value = "경제 사전 상세 페이지 순서", required = true)
    private int order;
}
