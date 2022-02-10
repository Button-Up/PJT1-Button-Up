package com.ssafy.buttonup.domain.service.user;

import com.ssafy.buttonup.domain.model.dto.user.request.ConnectRequest;
import com.ssafy.buttonup.domain.model.dto.user.request.JoinRequest;
import com.ssafy.buttonup.domain.model.dto.user.response.ChildResponse;
import com.ssafy.buttonup.domain.model.entity.user.Child;
import com.ssafy.buttonup.domain.model.entity.user.Parent;
import com.ssafy.buttonup.domain.repository.user.ChildRepository;
import com.ssafy.buttonup.domain.repository.user.ParentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 아이 관련 service
 *
 * @author jeongyeon woo,SeungYeon Hwang
 * created on 2022-02-03
 */

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ChildService {

    private final ChildRepository childRepository;
    private final ParentRepository parentRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * 회원가입
     *
     * @param joinRequest 회원가입 정보
     */

    public void join(JoinRequest joinRequest) {
        childRepository.save(Child.builder()
                .name(joinRequest.getName())
                .nickname(joinRequest.getNickname())
                .password(passwordEncoder.encode(joinRequest.getPassword()))
                .phone(joinRequest.getPhone())
                .auth("USER")           //최초가입시 USer로 설정
                .build());
    }

    /**
     * 아이 seq로 정보 조회
     *
     * @param childSeq 자녀
     * @return 아이
     */
    public ChildResponse findBySeq(long childSeq) {
        Child child = childRepository.getById(childSeq);
        ChildResponse childResponse = ChildResponse.builder()
                .seq(child.getSeq())
                .phone(child.getPhone())
                .name(child.getName())
                .birthDate(child.getBirthDate())
                .image(child.getImage())
                .parentSeq(child.getParent().getSeq())
                .build();
        return childResponse;
    }

    /**
     * 해당 부모의 아이 리스트 조회
     * @param parentSeq
     * @return 아이 리스트
     */

    public List<ChildResponse> findByParent(long parentSeq) {
        Parent parent = parentRepository.getById(parentSeq);
        List<Child> children = childRepository.findByParentOrderByBirthDateAsc(parent);
        List<ChildResponse> childrenResponse = new ArrayList<>();
        for(Child child : children) {
            ChildResponse childResponse = ChildResponse.builder()
                    .seq(child.getSeq())
                    .phone(child.getPhone())
                    .name(child.getName())
                    .birthDate(child.getBirthDate())
                    .image(child.getImage())
                    .build();
            childrenResponse.add(childResponse);
        }
        return childrenResponse;
    }

    /**
     * 아이와 부모 연결(아이의 부모 외래키에 부모 저장)
     * @param connectRequest
     */

    @Transactional
    public void connectWithParent(ConnectRequest connectRequest) {
        Child child = childRepository.findByNickname(connectRequest.getNickname())
                        .orElseThrow(()->new IllegalArgumentException("가입되지 않은 nickname입니다."));
        Parent parent = parentRepository.getById(connectRequest.getParentSeq());

        child.connectParent(parent);
        childRepository.save(child);
    }
}
