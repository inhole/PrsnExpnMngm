package com.spring.prsnexpnmngm.service;

import com.spring.prsnexpnmngm.model.User;
import com.spring.prsnexpnmngm.repository.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailService implements UserDetailsService {

    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 비밀번호 검증 및 체크 로직
        User user = userMapper.selectUserId(username);
        if (user == null) {
            return null;
        } else {
            return user;
        }
    }
}
