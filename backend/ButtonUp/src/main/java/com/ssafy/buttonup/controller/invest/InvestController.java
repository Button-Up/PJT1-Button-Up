package com.ssafy.buttonup.controller.invest;

import com.ssafy.buttonup.domain.model.dto.invest.request.StockRequest;
import com.ssafy.buttonup.domain.model.dto.invest.response.StockPresetResponse;
import com.ssafy.buttonup.domain.model.dto.invest.response.StockResponse;
import com.ssafy.buttonup.domain.service.invest.InvestService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 주식 관련 컨트롤러
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
     * 주식 프리셋 리스트 보내주기
     *
     * @return 주식 프리셋 목록
     */
    @GetMapping
    @ApiOperation(value = "주식 프리셋 목록 조회")
    public ResponseEntity<List<StockPresetResponse>> viewPresetList() {
        return new ResponseEntity<>(investService.getAllStockPreset(), HttpStatus.OK);
    }

    /**
     * 주식 정보 받아서 새로운 주식 추가
     *
     * @param request 새로운 주식 정보
     */
    @PostMapping
    @ApiOperation(value = "새로운 주식 추가", notes = "자녀 키로 입출금 계좌 잔액을 조회합니다.")
    @ApiResponses(
            @ApiResponse(code = 416, message = "이미 존재하는 주식")
    )
    public void addStock(@ApiParam(value = "새로운 주식 정보", required = true) @RequestBody StockRequest request) {
        investService.insertStock(request);
    }

    /**
     * 주식 리스트 조회
     *
     * @param parentSeq 부모 키
     * @return [주식 키, 주식 이름]으로 이루어진 리스트 전달
     */
    @GetMapping("{parent_seq}")
    @ApiOperation(value = "주식 목록 조회", notes = "부모 키로 주식 목록을 조회합니다.")
    public ResponseEntity<List<StockResponse>> viewInvestList(@ApiParam(value = "부모 키", required = true) @PathVariable("parent_seq") long parentSeq) {
        return new ResponseEntity<>(investService.getAllStockForParent(parentSeq), HttpStatus.OK);
    }
}
