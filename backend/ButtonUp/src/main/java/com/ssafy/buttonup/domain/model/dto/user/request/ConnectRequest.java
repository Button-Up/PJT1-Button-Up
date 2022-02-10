package com.ssafy.buttonup.domain.model.dto.user.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * 아이와 부모 연결 Request DTO
 *
 * @author jeongyeou woo
 * created on 2022-02-03
 */
@ApiModel(value = "부모와 자녀 연결 요청 정보")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConnectRequest {
    /* 연결하고자 하는 부모 아이디 */
    @ApiModelProperty(value = "부모 아이디", required = true)
    private String parentNickname;

    /* 연결하고자 하는 자녀 아이디 */
    @ApiModelProperty(value = "자녀 아이디", required = true)
    private String nickname;
}
