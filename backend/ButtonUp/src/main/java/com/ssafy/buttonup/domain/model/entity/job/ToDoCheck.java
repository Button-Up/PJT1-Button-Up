package com.ssafy.buttonup.domain.model.entity.job;

import com.ssafy.buttonup.domain.model.entity.user.Child;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

/**
 * 직업 할 일을 했는지 체크하는 체크리스트 엔티티
 *
 * @author SeungYeon Hwang
 * created on 2022-02-04
 */
@Entity
@Table(name = "todo_check_list")
@Getter
@DynamicInsert
@NoArgsConstructor
public class ToDoCheck {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "todo_check_seq")
    private Long seq;

    @Column(name = "todo_check_flag")
    private boolean flag;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_todo_seq")
    private ToDo toDo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_child_seq")
    private Child child;

    @Builder
    public ToDoCheck(boolean flag, ToDo toDo, Child child){
        this.flag = flag;
        this.toDo = toDo;
        this.child = child;
    }

    public void changeFlag(boolean flag){
        this.flag=flag;
    }
}