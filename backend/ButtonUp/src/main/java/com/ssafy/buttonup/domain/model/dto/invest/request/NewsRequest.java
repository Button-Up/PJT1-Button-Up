package com.ssafy.buttonup.domain.model.dto.invest.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 뉴스 등록 요청 DTO
 *
 * @author Jiun Kim
 * created on 2022-02-14
 */
@ApiModel(value = "뉴스 등록 요청 정보")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewsRequest {
    /* 뉴스 내용 */
    @ApiModelProperty(value = "뉴스 내용", required = true)
    private String content;

    /* 부모 키 */
    @ApiModelProperty(value = "부모 키", required = true)
    private long parentSeq;
}
