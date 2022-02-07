package com.ssafy.buttonup.domain.model.entity.request;

import com.ssafy.buttonup.domain.model.entity.Child;
import com.ssafy.buttonup.domain.model.entity.product.Product;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * 요청 내역 엔티티
 *
 * @author jeongyeon woo
 * created on 2022-02-07
 */

@Entity
@Table(name="request_histories")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="request_history_seq")
    private Long seq;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name="request_history_type")
    private RequestHistoryType type;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="request_history_date")
    private Date date;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name="request_history_status")
    private RequestHistoryStatus status;

    @NotNull
    @Column(name="request_history_price")
    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_product_seq")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_child_seq")
    private Child child;
}
