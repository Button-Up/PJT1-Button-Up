package com.ssafy.buttonup.domain.model.entity.job;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 직업에 대한 할 일 엔티티
 *
 * @author SeungYeon Hwang
 * created on 2022-02-04
 */
@Entity
@Table(name = "todo_list")
@Getter
@DynamicInsert
@NoArgsConstructor
public class ToDo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "todo_seq")
    private Long seq;

    @Column(name = "todo_content")
    private String content;

    @ManyToOne
    @JoinColumn(name = "fk_job_seq")
    private Job job;

//    @OneToMany(mappedBy = "toDo", cascade = CascadeType.ALL)
//    private List<ToDoCheck> toDoChecks = new ArrayList<>();

    @Builder
    public ToDo(String content, Job job){
        this.content=content;
        this.job=job;
    }
}