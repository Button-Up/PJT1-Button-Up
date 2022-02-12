package com.ssafy.buttonup.domain.service.invest;

import com.ssafy.buttonup.domain.model.dto.invest.request.StockRequest;
import com.ssafy.buttonup.domain.model.dto.invest.response.StockPresetResponse;
import com.ssafy.buttonup.domain.model.dto.invest.response.RoughStockResponse;
import com.ssafy.buttonup.domain.model.entity.invest.SharePrice;
import com.ssafy.buttonup.domain.model.entity.invest.Stock;
import com.ssafy.buttonup.domain.model.entity.invest.StockPreset;
import com.ssafy.buttonup.domain.model.entity.invest.StockStatus;
import com.ssafy.buttonup.domain.model.entity.user.Child;
import com.ssafy.buttonup.domain.model.entity.user.Parent;
import com.ssafy.buttonup.domain.repository.invest.SharePriceRepository;
import com.ssafy.buttonup.domain.repository.invest.StockPresetRepository;
import com.ssafy.buttonup.domain.repository.invest.StockRepository;
import com.ssafy.buttonup.domain.repository.invest.StockStatusRepository;
import com.ssafy.buttonup.domain.repository.user.ChildRepository;
import com.ssafy.buttonup.domain.repository.user.ParentRepository;
import com.ssafy.buttonup.exception.ExistStockException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 투자와 관련된 서비스 클래스
 *
 * @author Jiun Kim
 * created on 2022-02-12
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class InvestService {
    private final StockRepository stockRepository;
    private final SharePriceRepository priceRepository;
    private final StockStatusRepository statusRepository;
    private final StockPresetRepository presetRepository;
    private final ParentRepository parentRepository;
    private final ChildRepository childRepository;

    /**
     * 프리셋 목록 조회
     *
     * @return 주식 프리셋 목록
     */
    public List<StockPresetResponse> getAllStockPreset() {
        List<StockPreset> stockPresets = presetRepository.findAll();
        List<StockPresetResponse> responses = new ArrayList<>();
        for (StockPreset preset :
                stockPresets) {
            responses.add(preset.toResponse());
        }
        return responses;
    }

    /**
     * 주식 추가
     *
     * @param request 추가할 주식 정보
     */
    @Transactional
    public void insertStock(StockRequest request) throws ExistStockException {
        Parent parent = parentRepository.getById(request.getParentSeq());
        StockPreset preset = presetRepository.getById(request.getStockPresetSeq());

        // 이미 존재하는 주식인지 확인
        Stock exist = stockRepository.findByTargetAndStockPresetAndParent(request.getTarget(), preset, parent);
        if(exist == null) {
            // 주식 등록
            Stock stock = stockRepository.save(Stock.builder()
                    .target(request.getTarget())
                    .stockPreset(preset)
                    .parent(parent)
                    .build());

            // 시세 추가
            priceRepository.save(SharePrice.builder()
                    .price(request.getPrice())
                    .stock(stock)
                    .build());

            // 모든 자녀에 대한 주식 현황 추가
            List<Child> children = childRepository.findByParentOrderByBirthDateAsc(stock.getParent());
            for (Child child :
                    children) {
                statusRepository.save(StockStatus.builder()
                        .count(0)
                        .averagePrice(0)
                        .stock(stock)
                        .child(child)
                        .build());
            }
        } else throw new ExistStockException("이미 존재하는 종목");

    }

    /**
     * @param parentSeq 부모 키
     * @return 주식 목록
     */
    public List<RoughStockResponse> getAllStockForParent(long parentSeq) {
        List<Stock> stocks = stockRepository.findByParent_Seq(parentSeq);
        List<RoughStockResponse> responses = new ArrayList<>();
        for (Stock stock :
                stocks) {
            responses.add(stock.toRoughStockResponse());
        }
        return responses;
    }
}
