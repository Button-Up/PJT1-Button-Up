package com.ssafy.buttonup.controller.user;

import com.ssafy.buttonup.config.security.JwtTokenProvider;
import com.ssafy.buttonup.domain.model.dto.user.request.JoinRequest;
import com.ssafy.buttonup.domain.model.dto.user.request.LoginRequest;
import com.ssafy.buttonup.domain.model.dto.user.request.TutorialRequest;
import com.ssafy.buttonup.domain.model.dto.user.response.ParentResponse;
import com.ssafy.buttonup.domain.model.dto.user.response.TutorialResponse;
import com.ssafy.buttonup.domain.model.entity.user.Parent;
import com.ssafy.buttonup.domain.repository.user.ParentRepository;
import com.ssafy.buttonup.domain.service.user.ParentService;
import com.ssafy.buttonup.domain.service.user.TutorialService;
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
 * @author SeungYeon Hwang,jeongyeon woo
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
    private final TutorialService tutorialService;

    /**
     * 부모 회원가입
     *
     * @param joinRequest 회원가입 정보
     */

    @PostMapping("/join")
    @ApiOperation(value="부모 회원가입", notes="새로운 부모 회원을 등록합니다.")
    public void join(@ApiParam(value = "부모 회원가입 정보", required = true) @RequestBody JoinRequest joinRequest){
        parentService.join(joinRequest);
    }

    /**
     * 부모 튜토리얼 단계정보 조회
     *
     * @param parentSeq 부모 키
     * @return TutorialResponse 튜토리얼 응답 Dto
     */

    @GetMapping("/tutorial/{parent_seq}")
    @ApiOperation(value = "튜토리얼 조회",notes = "튜토리얼 완료 여부와 현재 단계 조회")
    public ResponseEntity<TutorialResponse> getTutorial(@ApiParam(value = "부모 키",required = true) @PathVariable("parent_seq") long parentSeq){
        return new ResponseEntity<>(tutorialService.getTutorialStage(parentSeq), HttpStatus.OK);
    }

    /**
     * 부모 튜토리얼 단계정보 수정
     *
     * @param tutorialRequest 튜토리얼 정보 Dto
     */
    
    @PutMapping("/tutorial")
    @ApiOperation(value = "튜토리얼 단계 수정",notes = "튜토리얼 단계 수정")
    public void updateTutorial(@ApiParam(value = "튜토리얼 단계 수정",readOnly = true) @RequestBody TutorialRequest tutorialRequest){
        tutorialService.updateTutorialStage(tutorialRequest);
    }


    /**
     * 부모 로그인
     *
     * @param loginRequest 로그인 정보
     * @return Jwt Token
     */
    
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

    /**
     * 부모 상세 조회
     *
     * @param parentSeq 부모 키
     * @return 부모
     */

    @GetMapping("/{parent_seq}")
    @ApiOperation(value="부모 상세 조회", notes="부모 키로 부모 정보를 조회합니다.")
    public ResponseEntity<ParentResponse> findBySeq(@ApiParam(value = "부모 키", required = true, example = "1") @PathVariable("parent_seq") long parentSeq) {
        return new ResponseEntity<>(parentService.findBySeq(parentSeq), HttpStatus.OK);
    }
}
