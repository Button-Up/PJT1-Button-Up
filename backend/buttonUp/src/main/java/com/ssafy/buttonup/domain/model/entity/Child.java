package com.ssafy.buttonup.domain.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "children")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class Child {
    @Id
    @GeneratedValue
    @Column(name = "child_seq")
    private final long seq;

    @Column(name = "child_phone")
    private String phone;

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
}
