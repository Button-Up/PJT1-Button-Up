package com.ssafy.buttonup.controller.user;

import com.ssafy.buttonup.config.security.JwtTokenProvider;
import com.ssafy.buttonup.domain.model.dto.user.request.ConnectRequest;
import com.ssafy.buttonup.domain.model.dto.user.request.JoinRequest;
import com.ssafy.buttonup.domain.model.dto.user.request.LoginRequest;
import com.ssafy.buttonup.domain.model.dto.user.response.ChildResponse;
import com.ssafy.buttonup.domain.model.entity.Child;
import com.ssafy.buttonup.domain.repository.user.ChildRepository;
import com.ssafy.buttonup.domain.service.user.ChildService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final ChildRepository childRepository;
    private final ChildService childService;

    //회원가입
    @PostMapping("/join")
    public HttpStatus join(@RequestBody JoinRequest joinRequest){
        childService.join(joinRequest);
        return HttpStatus.OK;
    }

    //로그인
    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest){
        Child member = childRepository.findByNickname(loginRequest.getNickname())
                .orElseThrow(()->new IllegalArgumentException("가입되지 않은 nickname입니다."));
        if(!passwordEncoder.matches(loginRequest.getPassword(),member.getPassword())){
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }
        List<String> roles = new ArrayList<>();
        for(String role:member.getAuth().split(",")){
            roles.add(role);
        }
        return jwtTokenProvider.createToken(member.getUsername(),roles);
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
