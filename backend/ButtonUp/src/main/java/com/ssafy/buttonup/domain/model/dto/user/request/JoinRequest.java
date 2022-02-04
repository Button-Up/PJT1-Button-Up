package com.ssafy.buttonup.domain.model.dto.user.request;

import lombok.Builder;
import lombok.Getter;

/**
 * 회원가입 DTO
 *
 * @author SeungYeon Hwang
 * created on 2022-02-04
 */

@Getter
@Builder
public class JoinRequest {
    private String name;
    private String nickname;
    private String phone;
    private String email;
    private String password;
}
