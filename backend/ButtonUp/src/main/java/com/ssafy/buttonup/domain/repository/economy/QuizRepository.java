package com.ssafy.buttonup.domain.repository.economy;

import com.ssafy.buttonup.domain.model.entity.economy.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 퀴즈 레포지토리
 *
 * @author Eunee Chung
 * created on 2022-02-14
 */

public interface QuizRepository extends JpaRepository<Quiz, Long> {
}