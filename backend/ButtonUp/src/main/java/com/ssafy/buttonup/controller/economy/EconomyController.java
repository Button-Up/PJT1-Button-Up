package com.ssafy.buttonup.controller.economy;

import com.ssafy.buttonup.domain.model.dto.invest.response.NewsResponse;
import com.ssafy.buttonup.domain.service.economy.EconomyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 경제 공부방 관련 컨트롤러
 *
 * @author eunee Chung
 * created on 2022-02-12
 */
@Api(tags = "경제 공부방 관련 기능")
@RestController
@RequestMapping("economy")
@RequiredArgsConstructor
public class EconomyController {
    private final EconomyService economyService;

    @GetMapping("/topic")
    @ApiOperation(value = "경제 주제 조회", notes = "")
    public ResponseEntity<List<String>> getEconomyTopic(){
        return new ResponseEntity<>(economyService.getEconomyTopic(), HttpStatus.OK);
    }
}
