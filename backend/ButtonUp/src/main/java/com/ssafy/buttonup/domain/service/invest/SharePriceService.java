package com.ssafy.buttonup.domain.service.invest;

import com.ssafy.buttonup.domain.model.dto.invest.response.SharePriceResponse;
import com.ssafy.buttonup.domain.model.entity.invest.Investment;
import com.ssafy.buttonup.domain.model.entity.invest.SharePrice;
import com.ssafy.buttonup.domain.repository.invest.InvestRepository;
import com.ssafy.buttonup.domain.repository.invest.SharePriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 투자 가격과 관련된 서비스 클래스
 *
 * @author Jiun Kim
 * created on 2022-02-14
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SharePriceService {
    private final SharePriceRepository priceRepository;
    private final InvestRepository investRepository;

    /**
     * 투자 가격 목록 조회
     *
     * @param investSeq 투자 키
     * @return 투자 가격 목록
     */
    public List<SharePriceResponse> getPricesForInvest(long investSeq) {
        List<SharePrice> sharePrices = priceRepository.findByInvestment_SeqOrderByDateDesc(investSeq);
        List<SharePriceResponse> responses = new ArrayList<>();
        for (SharePrice price :
                sharePrices) {
            responses.add(price.toResponse());
        }
        return responses;
    }

    /**
     * 시세 추가
     *
     * @param price      시세
     * @param investment 추가할 투자
     */
    public void insertPrice(int price, Investment investment) {
        priceRepository.save(SharePrice.builder()
                .price(price)
                .investment(investment)
                .build());
    }

    /**
     * 시세 추가
     *
     * @param price      시세
     * @param investSeq 추가할 투자 종목 키
     */
    public void insertPrice(int price, long investSeq) {
        priceRepository.save(SharePrice.builder()
                .price(price)
                .investment(investRepository.getById(investSeq))
                .build());
    }
}
