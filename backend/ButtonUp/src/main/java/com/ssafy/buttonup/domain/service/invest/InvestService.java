package com.ssafy.buttonup.domain.service.invest;

import com.ssafy.buttonup.domain.model.dto.invest.request.InvestRequest;
import com.ssafy.buttonup.domain.model.dto.invest.response.InvestPresetResponse;
import com.ssafy.buttonup.domain.model.dto.invest.response.RoughInvestResponse;
import com.ssafy.buttonup.domain.model.entity.invest.Investment;
import com.ssafy.buttonup.domain.model.entity.invest.InvestPreset;
import com.ssafy.buttonup.domain.model.entity.user.Child;
import com.ssafy.buttonup.domain.model.entity.user.Parent;
import com.ssafy.buttonup.domain.repository.invest.InvestStatusRepository;
import com.ssafy.buttonup.domain.repository.invest.SharePriceRepository;
import com.ssafy.buttonup.domain.repository.invest.InvestPresetRepository;
import com.ssafy.buttonup.domain.repository.invest.InvestRepository;
import com.ssafy.buttonup.domain.repository.user.ChildRepository;
import com.ssafy.buttonup.domain.repository.user.ParentRepository;
import com.ssafy.buttonup.domain.service.account.AccountService;
import com.ssafy.buttonup.exception.ExistInvestException;
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
public class InvestService extends InvestStatusService {
    private final InvestRepository investRepository;
    private final InvestPresetRepository presetRepository;
    private final ParentRepository parentRepository;
    private final ChildRepository childRepository;

    public InvestService(SharePriceRepository priceRepository, InvestRepository investRepository, InvestStatusRepository statusRepository, InvestRepository investRepository1, InvestPresetRepository presetRepository, ParentRepository parentRepository, ChildRepository childRepository, AccountService accountService) {
        super(priceRepository, investRepository, statusRepository, accountService);
        this.investRepository = investRepository1;
        this.presetRepository = presetRepository;
        this.parentRepository = parentRepository;
        this.childRepository = childRepository;
    }

    /**
     * 프리셋 목록 조회
     *
     * @return 투자 프리셋 목록
     */
    public List<InvestPresetResponse> getAllInvestPreset() {
        List<InvestPreset> investPresets = presetRepository.findAll();
        List<InvestPresetResponse> responses = new ArrayList<>();
        for (InvestPreset preset :
                investPresets) {
            responses.add(preset.toResponse());
        }
        return responses;
    }

    /**
     * 투자 종목 추가
     *
     * @param request 추가할 종목 정보
     */
    @Transactional
    public void insertInvest(InvestRequest request) throws ExistInvestException {
        Parent parent = parentRepository.getById(request.getParentSeq());
        InvestPreset preset = presetRepository.getById(request.getInvestPresetSeq());

        // 이미 존재하는 종목인지 확인
        Investment exist = investRepository.findByTargetAndInvestPresetAndParent(request.getTarget(), preset, parent);
        if (exist == null) {
            // 새 투자 종목 등록
            Investment investment = investRepository.save(Investment.builder()
                    .target(request.getTarget())
                    .investPreset(preset)
                    .parent(parent)
                    .build());

            // 시세 추가
            insertPrice(request.getPrice(), investment);

            // 모든 자녀에 대한 투자 현황 추가
            List<Child> children = childRepository.findByParentOrderByBirthDateAsc(investment.getParent());
            for (Child child :
                    children) {
                insertInvestStatusByChild(investment, child);
            }
        } else throw new ExistInvestException("이미 존재하는 종목");

    }

    /**
     * 부모 키로 모든 투자 목록 조회
     *
     * @param parentSeq 부모 키
     * @return 투자 목록
     */
    public List<RoughInvestResponse> getAllInvestForParent(long parentSeq) {
        List<Investment> investments = investRepository.findByParent_Seq(parentSeq);
        List<RoughInvestResponse> responses = new ArrayList<>();
        for (Investment investment :
                investments) {
            responses.add(investment.toRoughInvestResponse());
        }
        return responses;
    }
}
