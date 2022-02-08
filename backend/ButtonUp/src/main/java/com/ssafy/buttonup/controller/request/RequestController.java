package com.ssafy.buttonup.controller.request;

import com.ssafy.buttonup.domain.model.dto.request.request.ExchangeRequest;
import com.ssafy.buttonup.domain.model.dto.request.response.RequestHistoryResponse;
import com.ssafy.buttonup.domain.model.entity.request.RequestHistoryStatus;
import com.ssafy.buttonup.domain.service.request.RequestService;
import com.ssafy.buttonup.exception.BalanceOverException;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 요청 내역 관련 기능
 *
 * @author jeongyeon woo
 * created on 2022-02-08
 */

@Api(tags = "요청 관련 기능")
@RestController
@RequestMapping("/requests")
@RequiredArgsConstructor
public class RequestController {

    private final RequestService requestService;

    /**
     * 환전 요청 내역 추가
     * 아이가 가지고 있는 단추 이상의 환전을 요청할 경우, 예외 발생
     * @param requestHistoryRequest
     * @throws BalanceOverException
     */
    @ApiResponses(
            @ApiResponse(code = 416, message = "잔액 초과")
    )
    @ApiOperation(value = "환전 요청 추가", notes = "환전에 대한 요청 내역을 추가합니다.")
    @PostMapping("/exchange")
    public void exchange(@ApiParam(value = "환전 요청 정보", required = true) @RequestBody ExchangeRequest requestHistoryRequest) throws BalanceOverException {
        requestService.insertExchange(requestHistoryRequest);
    }

    /**
     * 요청 상태를 승인으로 변경
     * @param requestSeq
     * @throws BalanceOverException
     */
    @ApiResponses(
            @ApiResponse(code = 416, message = "잔액 초과")
    )
    @ApiOperation(value = "요청 상태 승인으로 변경", notes = "해당 요청 내역의 상태를 승인으로 변경합니다.")
    @PutMapping("/status/approve")
    public void changeStatusApprove(@ApiParam(value = "요청 내역 키", required = true) @RequestBody long requestSeq) throws BalanceOverException {
        requestService.changeStatus(requestSeq, RequestHistoryStatus.APPROVE);
    }

    /**
     * 요청 상태를 거절로 변경
     * @param requestSeq
     * @throws BalanceOverException
     */
    @ApiResponses(
            @ApiResponse(code = 416, message = "잔액 초과")
    )
    @ApiOperation(value = "요청 상태 거절으로 변경", notes = "해당 요청 내역의 상태를 거절으로 변경합니다.")
    @PutMapping("/status/reject")
    public void changeStatusReject(@ApiParam(value = "요청 내역 키", required = true) @RequestBody long requestSeq) throws BalanceOverException {
        requestService.changeStatus(requestSeq, RequestHistoryStatus.REJECT);
    }

    /**
     * 아이의 요청 내역 목록 조회
     * @param child_seq
     * @return
     */
    @ApiOperation(value = "아이의 요청 내역 목록 조회", notes = "아이 키로 요청 내역 목록을 조회합니다.")
    @GetMapping("/child/{child_seq}")
    public ResponseEntity<List<RequestHistoryResponse>> findByChild(@ApiParam(value = "아이 키", required = true) @PathVariable("child_seq") long child_seq) {
        return new ResponseEntity<>(requestService.findByChildSeq(child_seq), HttpStatus.OK);
    }

    /**
     * 해당 요청 내역 단일 조회
     * @param request_seq
     * @return
     */
    @ApiOperation(value = "요청 내역 조회", notes = "요청 내역 키로 요청 내역을 단일 조회합니다.")
    @GetMapping("/{request_seq}")
    public ResponseEntity<RequestHistoryResponse> findBySeq(@ApiParam(value = "요청 내역 키", required = true) @PathVariable("request_seq") long request_seq) {
        return new ResponseEntity<>(requestService.findBySeq(request_seq), HttpStatus.OK);
    }
}
