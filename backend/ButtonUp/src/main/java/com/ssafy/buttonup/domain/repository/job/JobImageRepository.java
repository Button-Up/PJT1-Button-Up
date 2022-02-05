package com.ssafy.buttonup.domain.repository.job;

import com.ssafy.buttonup.domain.model.entity.job.JobImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobImageRepository extends JpaRepository<JobImage, Long> {
}