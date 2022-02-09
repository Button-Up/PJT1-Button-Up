package com.ssafy.buttonup.domain.model.dto.user.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

/**
 * 로그인 응답 DTO
 *
 * @author SeungYeon Hwang
 * created on 2022-02-09
 */

@ApiModel(value = "로그인 응답 정보")
@Getter
@Builder
public class LoginResponse {

    /* 기본 키 */
    @ApiModelProperty(value = "기본 키",required = true)
    private final long seq;

    /* Jwt Token */
    @ApiModelProperty(value = "Jwt Token",required = true)
    private final String token;
}
