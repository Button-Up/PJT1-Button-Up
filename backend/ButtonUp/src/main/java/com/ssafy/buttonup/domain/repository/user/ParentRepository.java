package com.ssafy.buttonup.domain.repository.user;

import com.ssafy.buttonup.domain.model.entity.Parent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ParentRepository extends JpaRepository<Parent, Long> {

    Optional<Parent> findByNickname(String nickname);
}
