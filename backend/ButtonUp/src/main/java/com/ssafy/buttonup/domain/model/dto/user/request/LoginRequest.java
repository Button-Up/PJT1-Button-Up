package com.ssafy.buttonup.domain.model.dto.user.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

/**
 * 로그인 DTO
 *
 * @author SeungYeon Hwang
 * created on 2022-02-04
 */

@ApiModel(value="로그인 요청 정보")
@Getter
@Builder
public class LoginRequest {
    /* 닉네임 */
    @ApiModelProperty(value = "닉네임", required = true)
    private String nickname;

    /* 비밀번호 */
    @ApiModelProperty(value = "비밀번호", required = true)
    private String password;
}
