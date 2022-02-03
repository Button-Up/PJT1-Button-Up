package com.ssafy.buttonup.domain.repository.user;

import com.ssafy.buttonup.domain.model.entity.Child;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ChildRepository extends JpaRepository<Child, Long> {
}
