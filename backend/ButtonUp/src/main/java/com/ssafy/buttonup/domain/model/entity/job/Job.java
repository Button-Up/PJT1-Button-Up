package com.ssafy.buttonup.domain.model.entity.job;

import com.ssafy.buttonup.domain.model.entity.Parent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

/**
 * 직업 엔티티
 *
 * @author jiun kim
 * created on 2022-02-04
 */
@Entity
@Table(name = "jobs")
@Getter
@Builder
@DynamicInsert
@AllArgsConstructor
@NoArgsConstructor
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_seq")
    private Long seq;

    @Column(name = "job_pay_term")
    private int payTerm;

    @Column(name = "job_name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_job_image_seq")
    private JobImage jobImage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_parent_seq")
    private Parent parent;
}