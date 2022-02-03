package com.ssafy.buttonup.domain.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "parents")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class Parent implements UserDetails {
    //SpringSecurity는 UserDetails 객체를 통해 권한 정보를 관리하기 때문에 Parent에 UserDetails를 구현하고 추가 정보 재정의 해야함

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "parent_seq") 
    private final long seq;

    @Column(name = "parent_phone")
    private String phone;

    @Column(name = "parent_name")
    private String name;

    @Column(name = "parent_nickname")
    private String nickname;

    @Column(name = "parent_password")
    private String password;

    @Column(name = "parent_email")
    private String email;

    @Column(name = "parent_image")
    private String image;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<Child> children = new ArrayList<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return nickname;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
