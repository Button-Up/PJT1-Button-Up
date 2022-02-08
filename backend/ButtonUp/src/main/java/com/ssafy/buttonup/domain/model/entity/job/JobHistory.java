package com.ssafy.buttonup.domain.model.entity.job;

import com.ssafy.buttonup.domain.model.entity.user.Child;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

/**
 * 직업 내역 엔티티
 * 
 * @author jiun kim
 * created on 2022-02-04
 */
@Entity
@Table(name = "job_histories")
@Getter
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
public class JobHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_history_seq")
    private Long seq;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "job_history_start_date")
    private Date startDate;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "job_history_end_date")
    private Date endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_child_seq")
    private Child child;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_job_seq")
    private Job job;

    @Builder
    public JobHistory(Date endDate, Child child, Job job) {
        this.endDate = endDate;
        this.child = child;
        this.job = job;
    }

    public void updateRecentJobHistory() {
        this.endDate = new Date();
    }
}