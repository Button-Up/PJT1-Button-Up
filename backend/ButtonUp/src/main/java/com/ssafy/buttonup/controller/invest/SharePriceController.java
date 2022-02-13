package com.ssafy.buttonup.controller.invest;

import com.ssafy.buttonup.domain.model.dto.invest.request.SharePriceRequest;
import com.ssafy.buttonup.domain.model.dto.invest.response.SharePriceResponse;
import com.ssafy.buttonup.domain.service.invest.SharePriceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 투자 가격 관련 컨트롤러
 *
 * @author Jiun Kim
 * created on 2022-02-14
 */
@Api(tags = "시세(투자 가격) 관련 기능")
@RestController
@RequestMapping("share-price")
@RequiredArgsConstructor
public class SharePriceController {
    private final SharePriceService sharePriceService;

    /**
     * 현재 투자 가격 등록
     *
     * @param request 새로운 투자 정보
     */
    @PostMapping
    @ApiOperation(value = "새로운 투자 가격 등록")
    public void registerPrice(@ApiParam(value = "새로운 투자 정보", required = true) @RequestBody SharePriceRequest request) {
        sharePriceService.insertPrice(request.getPrice(), request.getInvestSeq());
    }

    /**
     * 종목별 시세 조회
     *
     * @param investSeq 투자 종목 키
     * @return 가격 목록
     */
    @GetMapping("{investment_seq}")
    @ApiOperation(value = "종목별 시세 조회")
    public ResponseEntity<List<SharePriceResponse>> viewSharePriceByInvest(@ApiParam(value = "투자 종목 키", required = true) @PathVariable("investment_seq") long investSeq) {
        return new ResponseEntity<>(sharePriceService.getPricesForInvest(investSeq), HttpStatus.OK);
    }
}
