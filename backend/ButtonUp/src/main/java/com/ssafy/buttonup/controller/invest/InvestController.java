package com.ssafy.buttonup.controller.invest;

import com.ssafy.buttonup.domain.model.dto.invest.request.InvestRequest;
import com.ssafy.buttonup.domain.model.dto.invest.request.InvestStatusRequest;
import com.ssafy.buttonup.domain.model.dto.invest.response.InvestPresetResponse;
import com.ssafy.buttonup.domain.model.dto.invest.response.RoughInvestResponse;
import com.ssafy.buttonup.domain.model.dto.invest.response.InvestStatusResponse;
import com.ssafy.buttonup.domain.service.invest.InvestService;
import com.ssafy.buttonup.exception.ExistInvestException;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 투자 관련 컨트롤러
 *
 * @author Jiun Kim
 * created on 2022-02-12
 */
@Api(tags = "투자 관련 기능")
@RestController
@RequestMapping("investments")
@RequiredArgsConstructor
public class InvestController {
    private final InvestService investService;

    /**
     * 투자 프리셋 리스트 보내주기
     *
     * @return 투자 프리셋 목록
     */
    @GetMapping
    @ApiOperation(value = "투자 프리셋 목록 조회")
    public ResponseEntity<List<InvestPresetResponse>> viewPresetList() {
        return new ResponseEntity<>(investService.getAllInvestPreset(), HttpStatus.OK);
    }

    /**
     * 투자 정보 받아서 새로운 투자 추가
     *
     * @param request 새로운 투자 정보
     */
    @PostMapping
    @ApiOperation(value = "새로운 투자 추가", notes = "자녀 키로 입출금 계좌 잔액을 조회합니다.")
    @ApiResponses(
            @ApiResponse(code = 400, message = "이미 존재하는 종목")
    )
    public void addInvest(@ApiParam(value = "새로운 투자 정보", required = true) @RequestBody InvestRequest request) throws ExistInvestException {
        investService.insertInvest(request);
    }

    /**
     * 투자 리스트 조회
     *
     * @param parentSeq 부모 키
     * @return [투자 키, 투자 이름]으로 이루어진 리스트 전달
     */
    @GetMapping("{parent_seq}")
    @ApiOperation(value = "투자 목록 조회", notes = "부모 키로 투자 목록을 조회합니다.")
    public ResponseEntity<List<RoughInvestResponse>> viewInvestList(@ApiParam(value = "부모 키", required = true) @PathVariable("parent_seq") long parentSeq) {
        return new ResponseEntity<>(investService.getAllInvestForParent(parentSeq), HttpStatus.OK);
    }

    /**
     * 자녀별 투자 종목별 현황 조회
     *
     * @param investSeq 투자 키
     * @param childSeq  자녀 키
     * @return 종목 현황 정보
     */
    @GetMapping("{investment_seq}/{child_seq}")
    public ResponseEntity<InvestStatusResponse> viewInvestDetail(@PathVariable("investment_seq") long investSeq, @PathVariable("child_seq") long childSeq) {
        return new ResponseEntity<>(investService.getInvest(investSeq, childSeq), HttpStatus.OK);
    }

    /**
     * 투자 현황 업데이트
     *
     * @param request 업데이트 정보
     */
    @PutMapping
    public void changeInvestStatus(@RequestBody InvestStatusRequest request) {
        investService.updateInvestStatus(request);
    }
}
