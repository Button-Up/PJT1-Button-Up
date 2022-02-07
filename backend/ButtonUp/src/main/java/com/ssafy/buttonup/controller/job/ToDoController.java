package com.ssafy.buttonup.controller.job;

import com.ssafy.buttonup.domain.model.dto.job.response.ToDoResponse;
import com.ssafy.buttonup.domain.service.job.ToDoService;
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
 * 할일 관련 컨트롤러
 *
 * @author SeungYeon Hwang
 * created on 2022-02-06
 */

@Api(tags = "할일 관련 기능")
@RequiredArgsConstructor
@RestController
@RequestMapping("/todo-list")
public class ToDoController {

    private final ToDoService toDoService;

    /**
     * 할일 리스트 조회
     *
     * @param jobSeq 직업 키
     * @return 할일 리스트
     */
    @GetMapping("/{job_seq}")
    @ApiOperation(value = "할일 리스트 조회", notes = "직업 키로 할일 리스트 조회")
    public ResponseEntity<List<ToDoResponse>> viewToDoList(@PathVariable("job_seq") long jobSeq){
        return new ResponseEntity<>(toDoService.getToDoList(jobSeq), HttpStatus.OK);
    }
}
