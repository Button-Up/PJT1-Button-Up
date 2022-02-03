package com.ssafy.buttonup.controller;

import com.ssafy.buttonup.domain.service.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 단추 관련 컨트롤러
 *
 * @author jiun kim
 * created on 2022-02-02
 */
@RestController
@RequestMapping("account")
public class AccountController {
    @Autowired
    AccountService accountService;

    @GetMapping("{child_seq}")
    public ResponseEntity<Integer> checkBalance(@PathVariable long child_seq) {
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(accountService.getBalanceByChild(child_seq), headers, HttpStatus.OK);
    }
}
