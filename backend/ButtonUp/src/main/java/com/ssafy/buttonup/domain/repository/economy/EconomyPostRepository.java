package com.ssafy.buttonup.domain.repository.economy;

import com.ssafy.buttonup.domain.model.entity.economy.EconomyPost;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 경제 사전 레포지토리
 *
 * @author Eunee Chung
 * created on 2022-02-14
 */

public interface EconomyPostRepository extends JpaRepository<EconomyPost, Long> {
}