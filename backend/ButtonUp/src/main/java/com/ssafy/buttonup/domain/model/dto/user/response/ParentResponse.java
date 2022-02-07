package com.ssafy.buttonup.domain.model.dto.user.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * 아이 Response dto
 *
 * @author jeongyeon woo
 * created on 2022-02-03
 */

@ApiModel(value="부모 응답 정보")
@Getter
@Setter
@Builder
@AllArgsConstructor
public class ParentResponse {
    /* 부모 키 */
    @ApiModelProperty(value = "부모 키", required = true)
    private long seq;

    /* 핸드폰 번호 */
    @ApiModelProperty(value = "부모 핸드폰번호", required = true)
    private String phone;

    /* 이름 */
    @ApiModelProperty(value = "부모 이름", required = true)
    private String name;

    /* 닉네임 */
    @ApiModelProperty(value = "부모 닉네임", required = true)
    private String nickName;

    /* 비밀번호 */
    @ApiModelProperty(value = "부모 비밀번호", required = true)
    private String password;

    /* 이메일 */
    @ApiModelProperty(value = "부모 이메일", required = true)
    private String email;

    /* 이미지 경로 */
    @ApiModelProperty(value = "부모 이미지 경로", required = true)
    private String image;
}
