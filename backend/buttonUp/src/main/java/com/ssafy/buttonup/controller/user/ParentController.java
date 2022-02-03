package com.ssafy.buttonup.controller.user;

import com.ssafy.buttonup.config.security.JwtTokenProvider;
import com.ssafy.buttonup.domain.model.entity.Parent;
import com.ssafy.buttonup.domain.repository.user.ParentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class ParentController {

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final ParentRepository parentRepository;

    //회원가입
    @PostMapping("/join")
    public Long join(@RequestBody Map<String,String> parent){
        return parentRepository.save(Parent.builder()
                .name(parent.get("name"))
                .nickname(parent.get("nickname"))
                .phone(parent.get("phone"))
                .email(parent.get("email"))
                .password(passwordEncoder.encode(parent.get("password")))
                .roles(Collections.singletonList("ROLE_USER"))      //최초가입시 USER로 설정
                .build()).getSeq();
    }

    //로그인
    @PostMapping("/login")
    public String login(@RequestBody Map<String,String> parent){
        Parent member = parentRepository.findByNickname(parent.get("nickname"))
                .orElseThrow(()->new IllegalArgumentException("가입되지 않은 nickname입니다."));
        if(!passwordEncoder.matches(parent.get("password"),member.getPassword())){
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }
        return  jwtTokenProvider.createToken(member.getUsername(), member.getRoles());
    }
}
