package com.ssafy.buttonup.domain.service.user;

import com.ssafy.buttonup.domain.model.dto.user.request.ConnectRequest;
import com.ssafy.buttonup.domain.model.dto.user.request.JoinRequest;
import com.ssafy.buttonup.domain.model.dto.user.response.ChildResponse;
import com.ssafy.buttonup.domain.model.entity.Child;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 아이 관련 service interface
 *
 * @author jeongyeon woo
 * created on 2022-02-03
 */
public interface ChildService {
    public void join(JoinRequest joinRequest);
    public ChildResponse findBySeq(long childSeq);
    public List<ChildResponse> findByParent(long parentSeq);
    public void connectWithParent(ConnectRequest connectRequest);
}
