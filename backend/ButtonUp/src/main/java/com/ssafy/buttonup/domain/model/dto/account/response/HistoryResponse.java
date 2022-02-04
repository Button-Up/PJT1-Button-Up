package com.ssafy.buttonup.domain.model.dto.account.response;

import com.ssafy.buttonup.domain.model.entity.account.AccountHistoryCategory;
import com.ssafy.buttonup.domain.model.entity.account.AccountHistoryType;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

/**
 * 입출금 내역 조회 응답 DTO
 *
 * @author jiun kim
 * created on 2022-02-04
 */
@Getter
@Builder
public class HistoryResponse {
    private final AccountHistoryType type;
    private final AccountHistoryCategory category;
    private final String content;
    private final int money;
    private final int balance;
    private final Date date;
}
