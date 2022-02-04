package com.ssafy.buttonup.domain.model.entity.job;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

/**
 * 직업 이미지 엔티티
 *
 * @author jiun kim
 * created on 2022-02-04
 */
@Entity
@Table(name = "job_images")
@Getter
@Builder
@DynamicInsert
@AllArgsConstructor
@NoArgsConstructor
public class JobImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_image_seq")
    private Long seq;

    @Column(name = "job_image")
    private String url;
}