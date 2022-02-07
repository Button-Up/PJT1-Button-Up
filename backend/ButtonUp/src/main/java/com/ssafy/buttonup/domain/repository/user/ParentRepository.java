package com.ssafy.buttonup.domain.repository.user;

import com.ssafy.buttonup.domain.model.entity.user.Parent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * 부모 레파지토리
 *
 * @author SeungYeon Hwang
 * created on 2022-02-03
 */

public interface ParentRepository extends JpaRepository<Parent, Long> {

    Optional<Parent> findByNickname(String nickname);
}
