package com.ssafy.buttonup.domain.repository.account;

import com.ssafy.buttonup.domain.model.entity.AccountHistory;
import com.ssafy.buttonup.domain.model.entity.Child;
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
     * 자녀의 단추 입출금 리스트 조회
     * @param child 자녀 엔티티
     * @return 입출금 내역
     */
    List<AccountHistory> findByChild(Child child);
}
