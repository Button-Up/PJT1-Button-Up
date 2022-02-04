package com.ssafy.buttonup.domain.model.dto.user.response;

import com.ssafy.buttonup.domain.model.entity.Parent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

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
public class ChildResponse {
    private long seq;

    private String phone;

    private String name;

    private Date birthDate;

    private String password;

    private String image;
}
