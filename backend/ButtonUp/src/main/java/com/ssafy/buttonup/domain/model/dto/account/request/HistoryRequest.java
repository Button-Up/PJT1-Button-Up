package com.ssafy.buttonup.domain.model.dto.account.request;

import com.ssafy.buttonup.domain.model.entity.AccountHistoryCategory;
import lombok.Builder;
import lombok.Getter;

/**
 * 입출금 내역 추가하는 DTO
 *
 * @author jiun kim
 * created on 2022-02-03
 */
@Getter
@Builder
public class HistoryRequest {
    private AccountHistoryCategory category;
    private String content;
    private int money;
    private long child_seq;
}
