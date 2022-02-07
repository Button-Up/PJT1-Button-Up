package com.ssafy.buttonup.domain.model.dto.user.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

/**
 * 회원가입 DTO
 *
 * @author SeungYeon Hwang
 * created on 2022-02-04
 */

@ApiModel(value="회원가입 요청 정보")
@Getter
@Builder
public class JoinRequest {
    /* 이름 */
    @ApiModelProperty(value = "이름", required = true)
    private String name;

    /* 닉네임 */
    @ApiModelProperty(value = "닉네임", required = true)
    private String nickname;

    /* 핸드폰 번호 */
    @ApiModelProperty(value = "핸드폰 번호", required = true)
    private String phone;

    /* 이메일 */
    @ApiModelProperty(value = "이메일", required = true)
    private String email;

    /* 비밀번호 */
    @ApiModelProperty(value = "비밀번호", required = true)
    private String password;
}
