package com.spring.prsnexpnmngm.service;

import com.spring.prsnexpnmngm.model.User;
import com.spring.prsnexpnmngm.repository.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserMapper userMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    public int insertUserId(User user) {
        // 패스워드 암호화
        user.setPw(bCryptPasswordEncoder.encode(user.getPw()));
        return userMapper.insertUserId(user);
    }
}
