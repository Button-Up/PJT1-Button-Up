package com.ssafy.buttonup.domain.model.dto.saving.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@ApiModel(value = "적금 내역")
@Getter
@Builder
public class SavingHistoryResponse {

    /* 입금한 날짜 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date date;

    /* 내용 */
    private String catetory;

    /* 입금한 금액 */
    private int money;

    /* 잔액 */
    private int balance;

}
