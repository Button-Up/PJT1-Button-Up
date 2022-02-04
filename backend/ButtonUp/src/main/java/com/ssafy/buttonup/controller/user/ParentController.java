package com.ssafy.buttonup.controller.user;

import com.ssafy.buttonup.config.security.JwtTokenProvider;
import com.ssafy.buttonup.domain.model.dto.user.request.JoinRequest;
import com.ssafy.buttonup.domain.model.dto.user.request.LoginRequest;
import com.ssafy.buttonup.domain.model.dto.user.response.ParentResponse;
import com.ssafy.buttonup.domain.model.entity.Parent;
import com.ssafy.buttonup.domain.repository.user.ParentRepository;
import com.ssafy.buttonup.domain.service.user.ParentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 부모 컨트롤러
 *
 * @author SeungYeon Hwang
 * created on 2022-02-03
 */

@RequiredArgsConstructor
@RestController
@RequestMapping("/parents")
public class ParentController {

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final ParentRepository parentRepository;
    private final ParentService parentService;

    //회원가입
    @PostMapping("/join")
    public HttpStatus join(@RequestBody JoinRequest joinRequest){
        parentService.join(joinRequest);
        return HttpStatus.OK;
    }

    //로그인
    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest){
        Parent member = parentRepository.findByNickname(loginRequest.getNickname())
                .orElseThrow(()->new IllegalArgumentException("가입되지 않은 nickname입니다."));
        if(!passwordEncoder.matches(loginRequest.getPassword(),member.getPassword())){
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }
        List<String> roles = new ArrayList<>();
        for (String role : member.getAuth().split(",")) {
            roles.add(role);
        }
        return  jwtTokenProvider.createToken(member.getUsername(), roles);
    }


    @GetMapping("/{parent_seq}")
    public ResponseEntity<ParentResponse> findBySeq(@PathVariable("parent_seq") int parentSeq) {
        return new ResponseEntity<>(parentService.findBySeq(parentSeq), HttpStatus.OK);
    }
}
