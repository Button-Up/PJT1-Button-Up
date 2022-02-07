package com.ssafy.buttonup.domain.repository.request;

import com.ssafy.buttonup.domain.model.entity.request.RequestHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepository extends JpaRepository<RequestHistory, Long> {
}
