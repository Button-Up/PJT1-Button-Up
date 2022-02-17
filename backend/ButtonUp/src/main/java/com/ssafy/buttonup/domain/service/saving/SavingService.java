package com.ssafy.buttonup.domain.service.saving;

import com.ssafy.buttonup.domain.model.dto.saving.request.SavingRequest;
import com.ssafy.buttonup.domain.model.dto.saving.response.SavingBalanceResponse;
import com.ssafy.buttonup.domain.model.dto.saving.response.SavingDetailResponse;
import com.ssafy.buttonup.domain.model.dto.saving.response.SavingHistoryResponse;
import com.ssafy.buttonup.domain.model.entity.saving.Saving;
import com.ssafy.buttonup.domain.model.entity.saving.SavingHistory;
import com.ssafy.buttonup.domain.repository.saving.SavingHistoryRepository;
import com.ssafy.buttonup.domain.repository.saving.SavingRepository;
import com.ssafy.buttonup.domain.repository.user.ChildRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

/**
 * 적금 관련 서비스
 *
 * @author eungchol kim
 * created on 2022-02-12
 */

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SavingService {
    private final SavingRepository savingRepository;
    private final SavingHistoryRepository savingHistoryRepository;
    private final ChildRepository childRepository;
    private final EntityManager entityManager;


    /**
     * 적금 통장 개설
     *
     * @param childSeq
     */
    public void insertSavingAccount(long childSeq) {
        Saving saving = savingRepository.save(Saving.builder()
                .interestRate(0.05)
                .term(100)
                .stateTypeFlag(true)
                .child(childRepository.getById(childSeq))
                        .build()
                );
        savingHistoryRepository.save(SavingHistory.builder()
                .saving(saving)
                .money(0)
                .balance(0)
                .catetory("개설")
                .build());
    }

    /**
     * 적금 상세 조회
     *
     * @param childSeq
     * @return
     */
    public SavingDetailResponse getSavingDetailInfo(long childSeq) {
        Saving savingSeq = savingRepository.findTopByChild_SeqOrderByDateDesc(childSeq);
        List<SavingHistory> historyList = savingHistoryRepository.findBySaving_SeqOrderByDateDesc(savingSeq.getSeq());
        List<SavingHistoryResponse> responseList = new ArrayList<>();
        for(SavingHistory history : historyList){
            responseList.add(history.toResponse());
        }

        Saving saving = savingRepository.getById(savingSeq.getSeq());
        return saving.toDetailResponse(responseList, savingSeq.getInterestRate());
    }

    /**
     * 적금 해지
     *
     * @param childSeq
     */
    @Transactional
    public Integer deleteSavingAccount(long childSeq) {
        Saving savingSeq = savingRepository.findTopByChild_SeqOrderByDateDesc(childSeq);
        SavingHistory recentHistory = savingHistoryRepository.findTopBySaving_SeqOrderByDateDesc(savingSeq.getSeq());

        savingSeq.changeState(false);

        savingRepository.save(savingSeq);

        LocalDate nowDate = LocalDate.now();
        LocalDate endDate = nowDate.plusDays(99);
        int restDate = Math.toIntExact(ChronoUnit.DAYS.between(nowDate, endDate));
        int refund = recentHistory.getBalance();
        if(restDate == 0)
            refund *= 1.05;

        return refund;

        // 이런 방식보다 클라이언트에서 확인버튼 만들어서 확인누르면 입금되게 하는 방식이
        // API 호출은 두번되지만 나을 것 같음.
    }

    /**
     * 적금 상태 및 잔액 조회
     *
     * @param childSeq
     * @return
     */
    public SavingBalanceResponse getSavingBalance(long childSeq) {
        Saving savingSeq = savingRepository.findTopByChild_SeqOrderByDateDesc(childSeq);
        if(savingSeq == null){
            SavingBalanceResponse savingBalanceResponse = SavingBalanceResponse.builder()
                    .StateType(false)
                    .balance(-1)
                    .build();

            return savingBalanceResponse;
        }else{
            SavingHistory recentHistory = savingHistoryRepository.findTopBySaving_SeqOrderByDateDesc(savingSeq.getSeq());

            if(savingSeq.isStateTypeFlag() == false){
                recentHistory.setBalance(-1);
            }

            SavingBalanceResponse savingBalanceResponse = SavingBalanceResponse.builder()
                    .StateType(savingSeq.isStateTypeFlag())
                    .balance(recentHistory.getBalance())
                    .build();

            return savingBalanceResponse;
        }

    }

    /**
     * 적금 입금
     *
     * @param request
     */
    @Transactional
    public void insertSavingAccountHistory(SavingRequest request) {
        Saving savingSeq = savingRepository.findTopByChild_SeqOrderByDateDesc(request.getChildSeq());
        SavingHistory recentHistory = savingHistoryRepository.findTopBySaving_SeqOrderByDateDesc(savingSeq.getSeq());

        SavingHistory savingHistory = SavingHistory.builder()
                .money(request.getTransferAmount())
                .catetory("입금")
                .balance(request.getTransferAmount() + recentHistory.getBalance())
                .saving(savingSeq)
                .build();

        savingHistoryRepository.save(savingHistory);
        entityManager.refresh(entityManager.merge(savingHistory));
    }
}
