package com.ssafy.buttonup.controller.user;

import com.ssafy.buttonup.domain.model.dto.user.request.ConnectRequest;
import com.ssafy.buttonup.domain.model.dto.user.request.JoinRequest;
import com.ssafy.buttonup.domain.model.dto.user.response.ChildResponse;
import com.ssafy.buttonup.domain.model.entity.Child;
import com.ssafy.buttonup.domain.service.user.ChildService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ChildController
 *
 * @author jeongyeon woo
 * created on 2022-02-03
 */
@RestController
@RequestMapping("/children")
@RequiredArgsConstructor
public class ChildController {
    private final ChildService childService;

    @PostMapping("/join")
    public HttpStatus join(@RequestBody JoinRequest joinRequest){
        childService.join(joinRequest);
        return HttpStatus.OK;
    }

    @GetMapping("/{child_seq}")
    public ResponseEntity<ChildResponse> findBySeq(@PathVariable("child_seq") int childSeq) {
        return new ResponseEntity<>(childService.findBySeq(childSeq), HttpStatus.OK);
    }

    @GetMapping("/parent/{parent_seq}")
    public ResponseEntity<List<ChildResponse>> findByParent(@PathVariable("parent_seq") int parentSeq) {
        return new ResponseEntity<>(childService.findByParent(parentSeq), HttpStatus.OK);
    }

    @PutMapping("/connect")
    public HttpStatus connectWithParent(@RequestBody ConnectRequest connectRequest) {
        childService.connectWithParent(connectRequest);
        return HttpStatus.OK;

    }
}
