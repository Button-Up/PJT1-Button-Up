package com.ssafy.buttonup.model.dto.memeber;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Children {
    @Id
    @GeneratedValue
    @Column(name = "child_phone")
    private String phone;

    @Column(name = "child_name")
    private String name;

    private int birth;

    private String password;

    private String image;

    @OneToOne   //객체매핑
    private Parents parent;
}
