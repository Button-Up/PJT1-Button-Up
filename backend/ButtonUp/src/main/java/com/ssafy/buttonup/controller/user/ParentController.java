package com.ssafy.buttonup.controller.user;

import com.ssafy.buttonup.domain.model.dto.user.response.ParentResponse;
import com.ssafy.buttonup.domain.model.entity.Parent;
import com.ssafy.buttonup.domain.service.user.ParentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ChildController
 *
 * @author jeongyeon woo
 * created on 2022-02-03
 */
@RestController
@RequestMapping("/parents")
@RequiredArgsConstructor
public class ParentController {
    public final ParentService parentService;
    @GetMapping("/{parent_seq}")
    public ResponseEntity<ParentResponse> findBySeq(@PathVariable("parent_seq") int parentSeq) {
        return new ResponseEntity<>(parentService.findBySeq(parentSeq), HttpStatus.OK);
    }
}
