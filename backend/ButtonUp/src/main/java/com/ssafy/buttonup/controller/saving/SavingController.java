package com.ssafy.buttonup.controller.saving;

import com.ssafy.buttonup.domain.model.dto.saving.request.SavingRequest;
import com.ssafy.buttonup.domain.model.dto.saving.response.SavingBalanceResponse;
import com.ssafy.buttonup.domain.model.dto.saving.response.SavingDetailResponse;
import com.ssafy.buttonup.domain.service.saving.SavingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * 적금 관련 컨트롤러
 *
 * @author eungchol kim
 * created on 2022-02-12
 */

@Api(tags = "적금 관련 기능")
@RestController
@RequestMapping("savings")
@RequiredArgsConstructor
public class SavingController {
    private final SavingService savingService;

    /**
     * 적금 통장 개설
     *
     * @param childSeq 키
     */
    @PostMapping("open/{child_seq}")
    @ApiOperation(value = "적금 통장 개설")
    public void openSavingAccount(@ApiParam(value = "자녀 키", required = true) @PathVariable("child_seq") long childSeq) {
        savingService.insertSavingAccount(childSeq);
    }

    /**
     * 적금 상세 정보 조회
     *
     * @param childSeq 키
     * @return
     */
    @GetMapping("detail/{child_seq}")
    @ApiOperation(value = "적금 상세 정보 조회")
    public ResponseEntity<SavingDetailResponse> viewSavingDetail(@ApiParam(value = "자녀 키", required = true) @PathVariable("child_seq") long childSeq) {
        return new ResponseEntity<>(savingService.getSavingDetailInfo(childSeq), HttpStatus.OK);
    }

    /**
     * 적금 통장 해지
     *
     * @param childSeq
     * @return
     */

    @DeleteMapping("close/{child_seq}")
    @ApiOperation(value = "적금 통장 해지")
    public ResponseEntity<Integer> closeSavingAccount(@ApiParam(value = "자녀 키", required = true)  @PathVariable("child_seq") long childSeq) {
        return new ResponseEntity<>(savingService.deleteSavingAccount(childSeq), HttpStatus.OK);
    }

    /**
     * 적금 잔액 조회
     *
     * @param childSeq
     * @return
     */
    @GetMapping("balance/{child_seq}")
    @ApiOperation(value = "적금 잔액 조회")
    public ResponseEntity<SavingBalanceResponse> getSavingBalance(@ApiParam(value = "자녀 키",required = true) @PathVariable("child_seq") long childSeq){
        return new ResponseEntity<>(savingService.getSavingBalance(childSeq), HttpStatus.OK);
    }

    /**
     * 적금 입금 & 내역 추가
     *
     * @param request
     */
    @PostMapping("histories/deposit")
    @ApiOperation(value = "적금 입금 내역 추가")
    public void addSavingAccountHistory(@ApiParam(value = "적금 입금 내역 추가 요청 정보", required = true) @RequestBody SavingRequest request){
        savingService.insertSavingAccountHistory(request);
    }

}
