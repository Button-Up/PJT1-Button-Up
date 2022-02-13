package com.ssafy.buttonup.domain.service.invest;

import com.ssafy.buttonup.domain.model.dto.invest.request.InvestStatusRequest;
import com.ssafy.buttonup.domain.model.dto.invest.response.InvestStatusResponse;
import com.ssafy.buttonup.domain.model.entity.invest.InvestStatus;
import com.ssafy.buttonup.domain.model.entity.invest.Investment;
import com.ssafy.buttonup.domain.model.entity.user.Child;
import com.ssafy.buttonup.domain.repository.invest.InvestRepository;
import com.ssafy.buttonup.domain.repository.invest.InvestStatusRepository;
import com.ssafy.buttonup.domain.repository.invest.SharePriceRepository;
import com.ssafy.buttonup.exception.BalanceOverException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 투자 가격과 관련된 서비스 클래스
 *
 * @author Jiun Kim
 * created on 2022-02-13
 */
@Service
@Transactional(readOnly = true)
public class InvestStatusService extends SharePriceService {
    private final InvestStatusRepository statusRepository;

    public InvestStatusService(SharePriceRepository priceRepository, InvestRepository investRepository, InvestStatusRepository statusRepository) {
        super(priceRepository, investRepository);
        this.statusRepository = statusRepository;
    }


    /**
     * 투자 종목별 현황 조회
     *
     * @param investSeq 투자 키
     * @param childSeq  자녀 키
     * @return 자녀별 해당 투자 상세 정보
     */
    public InvestStatusResponse getInvest(long investSeq, long childSeq) {
        // 가격 데이터와 같이 response로 변환
        return statusRepository.findByInvestment_SeqAndChild_Seq(investSeq, childSeq).toInvestStatusResponse(getPricesForInvest(investSeq));
    }

    /**
     * 투자 현황 수정
     *
     * @param request 투자 현황 업데이트 요청 정보
     */
    @Transactional
    public void updateInvestStatus(InvestStatusRequest request) throws BalanceOverException {
        InvestStatus status = statusRepository.getById(request.getSeq());
        status.buyOrSellInvest(request.getCount(), request.getPrice());
        statusRepository.save(status);
    }

    /**
     * 모든 자녀에 대한 투자 현황 추가
     * 
     * @param investment 투자
     * @param child 자녀
     */
    @Transactional
    public void insertInvestStatusByChild(Investment investment, Child child){
        statusRepository.save(InvestStatus.builder()
                .count(0)
                .averagePrice(0)
                .investment(investment)
                .child(child)
                .build());
    }
}
