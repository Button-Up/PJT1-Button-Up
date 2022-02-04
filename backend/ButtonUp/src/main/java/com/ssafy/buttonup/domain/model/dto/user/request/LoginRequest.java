package com.ssafy.buttonup.domain.model.dto.user.request;

import lombok.Builder;
import lombok.Getter;

/**
 * 로그인 DTO
 *
 * @author SeungYeon Hwang
 * created on 2022-02-04
 */

@Getter
@Builder
public class LoginRequest {
    private String nickname;
    private String password;
}
