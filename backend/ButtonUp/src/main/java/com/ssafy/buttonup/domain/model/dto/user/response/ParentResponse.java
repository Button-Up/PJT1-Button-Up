package com.ssafy.buttonup.domain.model.dto.user.response;

import com.ssafy.buttonup.domain.model.entity.Child;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 아이 Response dto
 *
 * @author jeongyeon woo
 * created on 2022-02-03
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
public class ParentResponse {
    private long seq;

    private String phone;

    private String name;

    private String nickName;

    private String password;

    private String email;

    private String image;
}
