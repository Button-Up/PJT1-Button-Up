package com.ssafy.buttonup.domain.repository.saving;

import com.ssafy.buttonup.domain.model.entity.saving.Saving;
import com.ssafy.buttonup.domain.model.entity.saving.SavingHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SavingRepository extends JpaRepository<Saving, Long> {

    Saving findTopByChild_SeqOrderByDateDesc(long childSeq);
}