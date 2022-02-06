package com.ssafy.buttonup.domain.model.entity.job;

import com.ssafy.buttonup.domain.model.dto.job.response.JobResponse;
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

    @Enumerated(EnumType.STRING)
    @Column(name = "job_pay_term")
    private PayTerm payTerm;

    @Column(name = "job_pay")
    private int pay;

    @Column(name = "job_name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_job_image_seq")
    private JobImage jobImage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_parent_seq")
    private Parent parent;

    /**
     * Job Entity를 Response dto로 바꿔주는 메서드
     *
     * @param job Job Entity
     * @return JobResponse
     */
    public static JobResponse ToResponse(Job job) {
        return JobResponse.builder()
                .seq(job.getSeq())
                .payTerm(job.getPayTerm())
                .pay(job.getPay())
                .name(job.getName())
                .jobImagePath(checkImageIsNull(job.getJobImage()))
                .build();
    }

    /**
     * 이미지 null 여부 체크 메서드
     *
     * @param jobImage 이미지 엔티티
     * @return 이미지 주소
     */
    private static String checkImageIsNull(JobImage jobImage) {
        return jobImage != null ? jobImage.getPath() : null;
    }
}