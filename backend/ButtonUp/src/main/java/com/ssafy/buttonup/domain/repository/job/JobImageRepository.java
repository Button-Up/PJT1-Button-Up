package com.ssafy.buttonup.domain.repository.job;

import com.ssafy.buttonup.domain.model.entity.job.JobImage;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 직업 이미지 레포지토리
 *
 * @author jiun kim
 * created on 2022-02-05
 */
public interface JobImageRepository extends JpaRepository<JobImage, Long> {
}