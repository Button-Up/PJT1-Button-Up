package com.ssafy.buttonup.domain.service.user;

import com.ssafy.buttonup.domain.repository.user.ParentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 토큰에 저장된 유저정보 활용 위한 클래스
 *
 * @author SeungYeon Hwang
 * created on 2022-02-03
 */

//UserDetailsService 상속받아서 재정의
@RequiredArgsConstructor
@Service
public class CustomuserDetailService implements UserDetailsService {

    private final ParentRepository parentRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        return parentRepository.findByNickname(username)
                .orElseThrow(()->new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
    }
}
