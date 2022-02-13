package com.ssafy.buttonup.controller.invest;

import com.ssafy.buttonup.domain.model.dto.invest.request.NewsRequest;
import com.ssafy.buttonup.domain.model.dto.invest.response.NewsResponse;
import com.ssafy.buttonup.domain.service.invest.NewsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 투자 뉴스 관련 컨트롤러
 *
 * @author Jiun Kim
 * created on 2022-02-14
 */
@Api(tags = "투자 뉴스 기능")
@RestController
@RequestMapping("news")
@RequiredArgsConstructor
public class NewsController {
    private final NewsService newsService;

    /**
     * 부모별 뉴스 목록 조회
     *
     * @param parentSeq 부모 키
     * @return 뉴스 목록
     */
    @GetMapping("{parent_seq}")
    @ApiOperation(value = "뉴스 목록 조회")
    public ResponseEntity<List<NewsResponse>> viewsNewsListByParent(@ApiParam(value = "부모 키", required = true) @PathVariable("parent_seq") long parentSeq) {
        return new ResponseEntity<>(newsService.getAllNewsListByParent(parentSeq), HttpStatus.OK);
    }

    /**
     * 새로운 뉴스 등록
     *
     * @param request 등록할 뉴스
     */
    @PostMapping
    @ApiOperation(value = "새로운 뉴스 등록")
    public void addNews(@ApiParam(value = "등록할 뉴스 정보", required = true) @RequestBody NewsRequest request) {
        newsService.insertNews(request);
    }
}
