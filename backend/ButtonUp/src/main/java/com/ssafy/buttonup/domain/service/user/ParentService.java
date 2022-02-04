package com.ssafy.buttonup.domain.service.user;

import com.ssafy.buttonup.domain.model.dto.user.response.ParentResponse;
import com.ssafy.buttonup.domain.model.entity.Child;
import com.ssafy.buttonup.domain.model.entity.Parent;
import org.springframework.stereotype.Service;

/**
 * 부모 관련 service interface
 *
 * @author jeongyeon woo
 * created on 2022-02-03
 */
@Service
public interface ParentService {
    public ParentResponse findBySeq(long parentSeq);
}
