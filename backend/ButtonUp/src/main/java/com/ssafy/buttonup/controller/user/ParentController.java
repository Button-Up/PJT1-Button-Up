package com.ssafy.buttonup.controller.user;

import com.ssafy.buttonup.config.security.JwtTokenProvider;
import com.ssafy.buttonup.domain.model.dto.user.request.JoinRequest;
import com.ssafy.buttonup.domain.model.dto.user.request.LoginRequest;
import com.ssafy.buttonup.domain.model.dto.user.response.ParentResponse;
import com.ssafy.buttonup.domain.model.entity.user.Parent;
import com.ssafy.buttonup.domain.repository.user.ParentRepository;
import com.ssafy.buttonup.domain.service.user.ParentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 부모 컨트롤러
 *
 * @author SeungYeon Hwang
 * created on 2022-02-03
 */

@Api(tags = "부모 관련 기능")
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
    @ApiOperation(value="부모 회원가입", notes="새로운 부모 회원을 등록합니다.")
    public HttpStatus join(@ApiParam(value = "부모 회원가입 정보", required = true) @RequestBody JoinRequest joinRequest){
        parentService.join(joinRequest);
        return HttpStatus.OK;
    }

    //로그인
    @PostMapping("/login")
    @ApiOperation(value="부모 로그인", notes="부모 닉네입과 비밀번호로 로그인을 해서 JWT 토큰을 받아옵니다.")
    public String login(@ApiParam(value = "부모 로그인 정보", required = true) @RequestBody LoginRequest loginRequest){
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
    @ApiOperation(value="부모 상세 조회", notes="부모 키로 부모 정보를 조회합니다.")
    public ResponseEntity<ParentResponse> findBySeq(@ApiParam(value = "부모 키", required = true, example = "1") @PathVariable("parent_seq") long parentSeq) {
        return new ResponseEntity<>(parentService.findBySeq(parentSeq), HttpStatus.OK);
    }
}
