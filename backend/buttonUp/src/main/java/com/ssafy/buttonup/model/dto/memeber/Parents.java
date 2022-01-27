package com.ssafy.buttonup.model.dto.memeber;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity     //테이블과의 매핑
//@Table  생략시 테이블명을 클래스명이랑 매핑
//@AllArgsConstructor     lombok 어노테이션, 모든 속성에 대해서 생성자를 만들어낸다.
//@Data       DTO 코드를 자동으로 생성(Getter,Setter,toString,default 생성자)
//부모객체와 자식객체의 toString()에서 무한반복 생길수있음 사용조심
@NoArgsConstructor      //기본 생성자
@Getter
@Setter
public class Parents {

    @Id     //기본키 매핑
    @GeneratedValue     //auto_increment
    @Column(name = "parent_phone")      //name 속성은 매핑할 컬럼 이름 지정
    private String phone;

    @Column(name = "parent_name")
    private String name;

    @Column(name = "parent_nickname")
    private String nickname;

    @Column(name = "parent_password")
    private String password;

    @Column(name = "parent_image")
    private String image;

    @Column(name = "parent_email")
    private String email;

    @Builder
    public Parents(String phone,String name, String nickname, String password, String image, String email){
        this.phone = phone;
        this.name = name;
        this.nickname = nickname;
        this.password = password;
        this.image = image;
        this.email = email;
    }

}
