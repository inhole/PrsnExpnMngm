package com.spring.prsnexpnmngm.controller;

import com.spring.prsnexpnmngm.model.Message;
import com.spring.prsnexpnmngm.model.User;
import com.spring.prsnexpnmngm.service.UserDetailService;
import com.spring.prsnexpnmngm.service.UserService;
import com.spring.prsnexpnmngm.util.CommonController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Slf4j
@RequiredArgsConstructor
@Controller
public class UserController extends CommonController {

    private final UserDetailService userDetailService;
    private final UserService userService;

    /** 홈 */
    @GetMapping("/")
    public String test() {
        log.info("테스트 컨트롤러");
        return "test";
    }

    /** 로그인 폼 */
    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("user", new User());
        return "user/login";
    }

    /** 회원가입 폼 */
    @GetMapping("/join")
    public String joinForm() {
        return "user/join";
    }

    /** 회원가입 */
    @PostMapping("/join")
    public String join(User user, Model model) {
        Message message;

        int result = userService.insertUserId(user);
        if (result > 0) {
            model.addAttribute("successMsg", "회원가입이 완료되었습니다.");
            message = new Message("회원가입이 완료되었습니다.", "/login", RequestMethod.GET, null);
        } else {
            message = new Message("회원가입이 실패하였습니다.", "/join", RequestMethod.GET, null);
        }
        return showMessageAndRedirect(message, model);
    }

    /** 로그아웃 */
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response,
                SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/login";
    }

}

