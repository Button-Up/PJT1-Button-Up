package com.ssafy.buttonup.domain.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "parents")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class Parent {
    @Id
    @GeneratedValue
    @Column(name = "parent_seq") 
    private final long seq;

    @Column(name = "parent_phone")
    private String phone;

    @Column(name = "parent_name")
    private String name;

    @Column(name = "parent_nickname")
    private String nickName;

    @Column(name = "parent_password")
    private String password;

    @Column(name = "parent_email")
    private String email;

    @Column(name = "parent_image")
    private String image;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<Child> children = new ArrayList<>();
}
