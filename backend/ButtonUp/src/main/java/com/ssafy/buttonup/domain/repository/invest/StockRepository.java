package com.ssafy.buttonup.domain.repository.invest;

import com.ssafy.buttonup.domain.model.entity.invest.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 주식 레포지토리
 *
 * @author Jiun Kim
 * created on 2022-02-12
 */
public interface StockRepository extends JpaRepository<Stock, Long> {
    List<Stock> findByParent_Seq(long parentSeq);
}