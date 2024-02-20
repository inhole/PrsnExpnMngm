package com.spring.prsnexpnmngm.util;

import com.spring.prsnexpnmngm.model.Message;
import com.spring.prsnexpnmngm.model.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;

import java.util.HashMap;
import java.util.Map;

public class CommonController {

    // 사용자에게 메시지를 전달하고, 페이지를 리다이렉트 한다
    public String showMessageAndRedirect(final Message params, Model model) {
        model.addAttribute("params", params);
        return "common/messageRedirect";
    }

    public static boolean isLogin() {
        boolean result = true;

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof String){
            result = false;
        }

        return result;
    }

    public static Map<String, Object> getUserInfo() {

        Map<String, Object> map = new HashMap<>();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        assert principal instanceof User;
        User user = (User) principal;

        Long userId = ((User) principal).getUserId();
        String id = ((User) principal).getUsername();
        String pw = ((User) principal).getPassword();

        map.put("userId", userId);
        map.put("id", id);
        map.put("pw", pw);

        return map;
    }
}
