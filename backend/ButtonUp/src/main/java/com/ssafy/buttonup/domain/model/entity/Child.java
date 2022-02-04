package com.ssafy.buttonup.domain.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "children")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class Child {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "child_seq")
    private final long seq;

    @Column(name = "child_phone")
    private String phone;

    @Column(name = "child_nickname")
    private String nickname;

    @Column(name = "child_name")
    private String name;

    @Temporal(TemporalType.DATE)
    @Column(name = "child_birth_date")
    private Date birthDate;

    @Column(name = "child_password")
    private String password;

    @Column(name = "child_image")
    private String image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_parent_seq")
    private Parent parent;

    @Column(name = "child_auth")
    private String auth;

    /**
     * 자식과 부모 객체에 서로의 정보 추가(저장)
     * @param parent
     * @author jeongyeon woo
     */
    public void connectParent(Parent parent) {
        this.parent = parent;
        parent.getChildren().add(this);
    }
}
