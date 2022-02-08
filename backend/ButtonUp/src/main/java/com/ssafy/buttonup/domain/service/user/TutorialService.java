package com.ssafy.buttonup.domain.service.user;


import com.ssafy.buttonup.domain.model.dto.user.request.TutorialRequest;
import com.ssafy.buttonup.domain.model.dto.user.response.TutorialResponse;
import com.ssafy.buttonup.domain.model.entity.user.Parent;
import com.ssafy.buttonup.domain.repository.user.ParentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 튜토리얼 서비스
 *
 * @author SeungYeon Hwang,jeongyeon woo
 * created on 2022-02-09
 */


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TutorialService {

    private ParentRepository parentRepository;

    /**
     * 부모 튜토리얼 단계정보 조회
     *
     * @param parentSeq 부모 키
     * @return TutorialResponse 튜토리얼 응답 Dto
     */

    public TutorialResponse getTutorialStage(long parentSeq){
        Parent parent = parentRepository.getById(parentSeq);

        TutorialResponse tutorialResponse = TutorialResponse.builder()
                .stage(parent.getStage())
                .build();
        return  tutorialResponse;
    }

    /**
     * 부모 튜토리얼 단계정보 수정
     *
     * @param tutorialRequest 튜토리얼 정보 Dto
     */

    public void updateTutorialStage(TutorialRequest tutorialRequest){
        Parent parent = parentRepository.getById(tutorialRequest.getParentSeq());

        parent.changeStage(tutorialRequest.getStage());
    }
}
