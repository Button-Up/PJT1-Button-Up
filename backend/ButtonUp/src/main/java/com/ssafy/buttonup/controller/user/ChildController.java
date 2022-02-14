package com.ssafy.buttonup.controller.user;

import com.ssafy.buttonup.config.security.JwtTokenProvider;
import com.ssafy.buttonup.domain.model.dto.user.request.ConnectRequest;
import com.ssafy.buttonup.domain.model.dto.user.request.JoinRequest;
import com.ssafy.buttonup.domain.model.dto.user.request.LoginRequest;
import com.ssafy.buttonup.domain.model.dto.user.response.ChildResponse;
import com.ssafy.buttonup.domain.model.dto.user.response.LoginResponse;
import com.ssafy.buttonup.domain.model.entity.user.Child;
import com.ssafy.buttonup.domain.repository.user.ChildRepository;
import com.ssafy.buttonup.domain.service.user.ChildService;
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
 * ChildController
 *
 * @author jeongyeon woo,SeungYeon Hwang
 * created on 2022-02-03
 */

@Api(tags = "아이 관련 기능")
@RestController
@RequestMapping("/children")
@RequiredArgsConstructor
public class ChildController {

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final ChildRepository childRepository;
    private final ChildService childService;

    /**
     * 자녀 회원가입
     *
     * @param joinRequest 회원가입 정보
     */
    
    @PostMapping("/join")
    @ApiOperation(value="아이 회원가입", notes="새로운 아이 회원을 등록합니다.")
    public void join(@ApiParam(value = "회원가입 정보", required = true) @RequestBody JoinRequest joinRequest){
        childService.join(joinRequest);
    }

    /**
     * 아이 로그인
     *
     * @param loginRequest 로그인 정보
     * @return 로그인 응답 정보
     */
    
    @PostMapping("/login")
    @ApiOperation(value="아이 로그인", notes="아이 닉네입과 비밀번호로 로그인을 해서 JWT 토큰을 받아옵니다.")
    public ResponseEntity<LoginResponse> login(@ApiParam(value = "로그인 정보", required = true) @RequestBody LoginRequest loginRequest){

        LoginResponse loginResponse = null;

        try{
            Child member = childRepository.findByNickname(loginRequest.getNickname())
                    .orElseThrow(()->new IllegalArgumentException("가입되지 않은 nickname입니다."));
            if(!passwordEncoder.matches(loginRequest.getPassword(),member.getPassword())){
                throw new IllegalArgumentException("잘못된 비밀번호입니다.");
            }
            List<String> roles = new ArrayList<>();
            for(String role:member.getAuth().split(",")){
                roles.add(role);
            }

            String token = jwtTokenProvider.createToken(member.getUsername(),roles);

            loginResponse = LoginResponse.builder()
                    .seq(member.getSeq())
                    .token(token)
                    .build();

        } catch (IllegalArgumentException i){
            return new ResponseEntity<>(loginResponse, HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(loginResponse, HttpStatus.OK);
    }

    /**
     * 아이 상세 조회
     *
     * @param childSeq 아이 키
     * @return 아이
     */
    
    @GetMapping("/{child_seq}")
    @ApiOperation(value="아이 상세 조회", notes="아이 키로 아이 정보를 조회합니다.")
    public ResponseEntity<ChildResponse> findBySeq(@ApiParam(value = "아이 키", required = true, example = "1") @PathVariable("child_seq") long childSeq) {
        return new ResponseEntity<>(childService.findBySeq(childSeq), HttpStatus.OK);
    }

    /**
     * 아이 리스트 조회
     *
     * @param parentSeq 부모 키
     * @return 아이 리스트
     */
    
    @GetMapping("/parent/{parent_seq}")
    @ApiOperation(value="해당 부모의 아이들을 조회", notes="부모 키로 아이들의 정보를 조회합니다.")
    public ResponseEntity<List<ChildResponse>> findByParent(@ApiParam(value = "부모 키", required = true, example = "1") @PathVariable("parent_seq") long parentSeq) {
        return new ResponseEntity<>(childService.findByParent(parentSeq), HttpStatus.OK);
    }

    /**
     * 아이 부모 연결
     *
     * @param connectRequest
     */

    @PutMapping("/connect")
    @ApiOperation(value="아이와 부모 연결", notes="아이를 부모의 자녀로 등록합니다.")
    public ResponseEntity<String> connectWithParent(@ApiParam(value = "아이와 부모 키", required = true) @RequestBody ConnectRequest connectRequest) {

        if(childService.connectWithParent(connectRequest)){
            return new ResponseEntity<String>("SUCESS",HttpStatus.OK);
        }
        return new ResponseEntity<String>("가입되지 않은 닉네임입니다.",HttpStatus.UNAUTHORIZED);
    }
}
