package com.ssafy.buttonup.domain.model.dto.user.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 아이 Response dto
 *
 * @author jeongyeon woo
 * created on 2022-02-03
 */

@ApiModel(value="아이 응답 정보")
@Getter
@Setter
@Builder
@AllArgsConstructor
public class ChildResponse {
    /* 자녀 키 */
    @ApiModelProperty(value = "아이 키", required = true)
    private long seq;

    /* 핸드폰 번호 */
    @ApiModelProperty(value = "아이 핸드폰번호", required = true)
    private String phone;

    /* 이름 */
    @ApiModelProperty(value = "아이 이름", required = true)
    private String name;

    /* 생년월일 */
    @ApiModelProperty(value = "아이 생년월일", required = true)
    private Date birthDate;

    /* 비밀번호 */
    @ApiModelProperty(value = "아이 비밀번호", required = true)
    private String password;

    /* 이미지 경로 */
    @ApiModelProperty(value = "아이 프로필 경로", required = true)
    private String image;
}
