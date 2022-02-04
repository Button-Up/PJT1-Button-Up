package com.ssafy.buttonup.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.List;

/**
 * JWT 생성하고 검증하는 컴포넌트
 *
 * @author SeungYeon Hwang
 * created on 2022-02-03
 */

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

    private String secretKey ="danchu";

    // 토큰 유효시간 1시간
    private long tokenValidTime = 60 * 60 *1000L;

    private final UserDetailsService userDetailsService;

    //객체 초기화, secretKey를 Base64로 인코딩
    @PostConstruct
    protected void init(){
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    //JWT 토큰 생성
    public String createToken(String userPk, List<String> roles){
        Claims claims = Jwts.claims().setSubject(userPk);   //JWT payload에 저장되는 정보단위
        claims.put("roles",roles);      //정보는 key,value 쌍으로 저장
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims)      //정보저장
                .setIssuedAt(now)       //토큰 발행 시간 정보
                .setExpiration(new Date(now.getTime()+tokenValidTime))      //set Exprie Time
                .signWith(SignatureAlgorithm.HS256, secretKey)      //사용할 암호화 알고리즘과
                                                                    //signature에 들어갈 secret값 세팅
                .compact();

    }

    // JWT 토큰에서 인증 정보 조회
    public Authentication getAuthentication(String token){
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUserPk(token));
        return new UsernamePasswordAuthenticationToken(userDetails,"",userDetails.getAuthorities());
    }

    //토큰에서 회원정보 추출
    public String getUserPk(String token){
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    // Request의 헤더에서 token 값 가져오기         //"X-AUTH-TOKEN" : "TOKEN값"
    public String resolveToken(HttpServletRequest request){
        return request.getHeader("X-AUTH-TOKEN");
    }

    //토큰의 유효성 + 만료일자 확인
    public boolean validateToken(String jwtToken){
        try{
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e){
            return false;
        }
    }




}
