package com.spring.prsnexpnmngm.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class PrincipalDetails implements UserDetails {
    private long userId;
    private String id;
    private String pw;
    private String name;
    private String email;
    private Date joinDt;

    /** 권한 반환 */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("user"));
    }

    /** 사용자의 id 반환 (고유 값) */
    @Override
    public String getUsername() {
        return id;
    }

    /** 사용자의 패스워드 반환 */
    @Override
    public String getPassword() {
        return pw;
    }

    /**
     * 계정 만료 여부 반환
     * <p> true : 만료 X </p>
     * */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 계정 잠금 여부 반환
     * <p> true : 잠금 X </p>
     * */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 패스워드 만료 여부 반환
     * <p> true : 만료 X </p>
     * */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 계정 사용 가능 여부 반환
     * <p> true : 사용 가능 </p>
     * */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
