package com.ssafy.buttonup.controller.job;

import com.ssafy.buttonup.domain.model.dto.job.response.ToDoCheckResponse;
import com.ssafy.buttonup.domain.service.job.ToDoCheckService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 체크리스트 관련 컨트롤러
 *
 * @author SeungYeon Hwang
 * created on 2022-02-08
 */

@Api(tags = "체크리스트 관련 기능")
@RequiredArgsConstructor
@RestController
@RequestMapping("/check-list")
public class ToDoCheckController {

    private final ToDoCheckService toDoCheckService;

    /**
     * 체크리스트 조회
     *
     * @param childSeq 아이 키
     * @return 체크리스트
     */
    @GetMapping("/{child_seq}")
    @ApiOperation(value = "체크리스트 조회", notes = "아이 키로 직업내역의 가장 최근직업(현재직업)을 가져오고 " +
            "현재 직업키로 할일 리스트를 가져온후에 각각의 할 일 키와 아이키로 체크리스트 가져옴")
    public ResponseEntity<List<ToDoCheckResponse>> viewCheckList(@PathVariable("child_seq") long childSeq){
        return new ResponseEntity<>(toDoCheckService.getCheckList(childSeq), HttpStatus.OK);
    }
}
