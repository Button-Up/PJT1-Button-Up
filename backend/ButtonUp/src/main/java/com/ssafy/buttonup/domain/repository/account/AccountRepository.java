package com.ssafy.buttonup.domain.repository.account;

import com.ssafy.buttonup.domain.model.entity.account.AccountHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 입출금 내역 레포지토리
 *
 * @author jiun kim
 * created on 2022-02-02
 */
public interface AccountRepository extends JpaRepository<AccountHistory, Long> {
    /**
     * 가장 최근 입출금 내역 하나만 조회
     *
     * @param childSeq 자녀 코드
     * @return 최근 입출금 내역
     */
    AccountHistory findTopByChild_SeqOrderByDateDesc(long childSeq);

    /**
     * 자녀의 단추 입출금 리스트 조회
     *
     * @param childSeq 자녀 코드
     * @return 입출금 내역 목록
     */
    List<AccountHistory> findByChild_SeqOrderByDateDesc(long childSeq);
}
