package com.ssafy.buttonup.domain.repository.request;

import com.ssafy.buttonup.domain.model.entity.request.RequestHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequestHistoryRepository extends JpaRepository<RequestHistory, Long> {
    List<RequestHistory> findByChild_SeqOrderByDateAsc(long childSeq);
}