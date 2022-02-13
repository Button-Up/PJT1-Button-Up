package com.ssafy.buttonup.domain.model.dto.invest.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

/**
 * 뉴스 응답 DTO
 *
 * @author Jiun Kim
 * created on 2022-02-14
 */
@ApiModel(value = "뉴스 응답 정보")
@Getter
@Builder
public class NewsResponse {
    /* 뉴스 등록일시 */
    @ApiModelProperty(value = "뉴스 등록일시", required = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final Date date;

    /* 뉴스 내용 */
    @ApiModelProperty(value = "뉴스 내용", required = true)
    private final String content;
}
