package com.ssafy.buttonup.domain.repository.user;

import com.ssafy.buttonup.domain.model.entity.Child;
import com.ssafy.buttonup.domain.model.entity.Parent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface ChildRepository extends JpaRepository<Child, Long> {

    Optional<Child> findByNickname(String nickname);

    /**
     * 부모로 아이 리스트를 생년월일 순으로 오름차순해서 반환 
     * @param parent
     * @return
     */
    List<Child> findByParentOrderByBirthDateAsc(Parent parent);
//    List<Child> findByParentAndSort(Parent parent, Sort sort);

}
