package com.ssafy.buttonup.domain.model.entity.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "children")
@Getter
@DynamicInsert
@NoArgsConstructor
public class Child implements UserDetails {
    //SpringSecurity는 UserDeatails 객체를 통해 권한 정보를 관리하기 때문에 Child에 UserDetails를 구현하고 추가 정보 재정의 해야함
    
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "child_seq")
    private Long seq;

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

    @Builder
    public Child(String phone, String nickname, String name, String password, String image, Parent parent, String auth,Date birthDate){
        this.phone=phone;
        this.nickname=nickname;
        this.name=name;
        this.password=password;
        this.image=image;
        this.parent=parent;
        this.auth=auth;
        this.birthDate=birthDate;
    }

//    @OneToMany(mappedBy = "child", cascade = CascadeType.ALL)
//    private List<AccountHistory> accountHistories = new ArrayList<>();

//    @OneToMany(mappedBy = "child", cascade = CascadeType.ALL)
//    private List<JobHistory> jobHistories = new ArrayList<>();

//    @OneToMany(mappedBy = "child", cascade = CascadeType.ALL)
//    private List<ToDoCheck> toDoChecks = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> roles = new HashSet<>();
        for(String role : auth.split(",")){
            roles.add(new SimpleGrantedAuthority(role));
        }
        return roles;
    }

    @Override
    public String getUsername() {
        return nickname;
    }

    //계정 만료 여부 반환
    @Override
    public boolean isAccountNonExpired() {
        return false;       //true가 유효
    }

    //계정 잠금 여부 반환
    @Override
    public boolean isAccountNonLocked() {
        return false;
    }
    
    //패스워드 만료 여부 반환
    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    //계정 사용 가능 여부 반환
    @Override
    public boolean isEnabled() {
        return false;
    }

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
