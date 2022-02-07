package com.ssafy.buttonup.domain.service.user;

import com.ssafy.buttonup.domain.model.dto.user.request.JoinRequest;
import com.ssafy.buttonup.domain.model.dto.user.response.ParentResponse;
import com.ssafy.buttonup.domain.model.entity.user.Parent;
import com.ssafy.buttonup.domain.repository.user.ParentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ParentServiceImpl implements ParentService {

    private final ParentRepository parentRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void join(JoinRequest joinRequest) {
        parentRepository.save(Parent.builder()
                .name(joinRequest.getName())
                .nickname(joinRequest.getNickname())
                .phone(joinRequest.getPhone())
                .email(joinRequest.getEmail())
                .password(passwordEncoder.encode(joinRequest.getPassword()))
                .auth("USER")      //최초가입시 USER로 설정
                .build());
    }

    /**
     * 부모 seq로 부모 조회
     * @param parentSeq
     * @return 부모
     */
    @Override
    public ParentResponse findBySeq(long parentSeq) {
        Parent parent = parentRepository.getById(parentSeq);
        ParentResponse parentResponse = ParentResponse.builder()
                .seq(parent.getSeq())
                .phone(parent.getPhone())
                .name(parent.getName())
                .nickName(parent.getNickname())
                .password(parent.getPassword())
                .email(parent.getEmail())
                .image(parent.getImage())
                .build();
        return parentResponse;
    }
}
