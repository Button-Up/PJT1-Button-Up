package com.ssafy.buttonup.controller.request;

import com.ssafy.buttonup.domain.model.dto.request.request.ExchangeRequest;
import com.ssafy.buttonup.domain.model.dto.request.response.RequestHistoryResponse;
import com.ssafy.buttonup.domain.model.entity.request.RequestHistoryStatus;
import com.ssafy.buttonup.domain.service.request.RequestService;
import com.ssafy.buttonup.exception.BalanceOverException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("요청 관련 기능")
@RestController
@RequestMapping("/requests")
@RequiredArgsConstructor
public class RequestController {

    private final RequestService requestService;

    @PostMapping("/exchange")
    @ApiResponses(
            @ApiResponse(code = 416, message = "잔액 초과")
    )
    public void exchange(@RequestBody ExchangeRequest requestHistoryRequest) throws BalanceOverException {
        requestService.insertExchange(requestHistoryRequest);
    }

    @PutMapping("/approve")
    @ApiResponses(
            @ApiResponse(code = 416, message = "잔액 초과")
    )
    public void changeStatusApprove(@RequestBody long requestSeq) throws BalanceOverException {
        requestService.changeStatus(requestSeq, RequestHistoryStatus.APPROVE);
    }

    @PutMapping("/reject")
    @ApiResponses(
            @ApiResponse(code = 416, message = "잔액 초과")
    )
    public void changeStatusReject(@RequestBody long requestSeq) throws BalanceOverException {
        requestService.changeStatus(requestSeq, RequestHistoryStatus.REJECT);
    }

    @GetMapping("/child/{child_seq}")
    public ResponseEntity<List<RequestHistoryResponse>> findByChild(@PathVariable("child_seq") long child_seq) {
        return new ResponseEntity<>(requestService.findByChildSeq(child_seq), HttpStatus.OK);
    }

    @GetMapping("/{request_seq}")
    public ResponseEntity<RequestHistoryResponse> findBySeq(@PathVariable("request_seq") long request_seq) {
        return new ResponseEntity<>(requestService.findBySeq(request_seq), HttpStatus.OK);
    }
}
