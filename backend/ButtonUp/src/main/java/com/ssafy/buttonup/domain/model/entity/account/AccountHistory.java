package com.ssafy.buttonup.domain.model.entity.account;

import com.ssafy.buttonup.domain.model.dto.account.response.HistoryResponse;
import com.ssafy.buttonup.domain.model.entity.user.Child;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * 입출금 내역 엔티티
 *
 * @author jiun kim
 * created on 2022-02-02
 */
@Entity
@Table(name = "account_histories")
@Getter
@DynamicInsert
@NoArgsConstructor
public class AccountHistory {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "account_history_seq")
    private Long seq;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_history_type")
    private AccountHistoryType type;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_history_category")
    private AccountHistoryCategory category;

    @Column(name = "account_history_content")
    private String content;

    @Column(name = "account_history_money")
    private int money;

    @Column(name = "account_history_balance")
    private int balance;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "account_history_date")
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_child_seq")
    private Child child;

    @Builder
    public AccountHistory(AccountHistoryType type, AccountHistoryCategory category, String content, int money, int balance, Child child) {
        this.type = type;
        this.category = category;
        this.content = content;
        this.money = money;
        this.balance = balance;
        this.child = child;
    }

    /**
     * AccountHistory Entity를 Response dto로 바꿔주는 메서드
     *
     * @param history AccountHistory Entity
     * @return HistoryResponse
     */
    public static HistoryResponse toResponse(AccountHistory history) {
        return HistoryResponse.builder()
                .type(history.getType())
                .category(history.getCategory())
                .content(history.getContent())
                .money(history.getMoney())
                .balance(history.getBalance())
                .date(history.getDate())
                .build();
    }
}
