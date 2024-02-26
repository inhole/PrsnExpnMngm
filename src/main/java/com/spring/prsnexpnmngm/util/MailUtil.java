package com.spring.prsnexpnmngm.util;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@Slf4j
@RequiredArgsConstructor
public class MailUtil {
    private final JavaMailSender mailSender;

    /** 이메일 인증 */
    public String joinEmail(String email) throws MessagingException {
        String setFrom = "이인호";
        String toMail = email; // 보낼 사람
        String title = "[개인 지출 관리] 회원가입 인증 이메일";
        int authNumber = makeRandomNumber();
        String content =
                        "</br></br>" +
                        "인증번호는 " + authNumber + " 입니다." +
                        "</br></br>";
        sendMail(setFrom, toMail, title, content);
        return Integer.toString(authNumber);
    }

    /** 난수번호 생성 */
    private int makeRandomNumber() {
        Random r = new Random();
        int checkNum = r.nextInt(888888) + 111111;  // 111111 ~ 99999 사이의 난수 발생
        log.info("이메일 인증번호 생성 : " + checkNum);
        return checkNum;
    }

    /** 메일 송신 */
    private void sendMail(String setFrom, String toMail, String title, String content)  throws MessagingException{
        MimeMessage message = mailSender.createMimeMessage();   // 스프링에서 제공하는 메일 API
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
        helper.setFrom(setFrom);
        helper.setTo(toMail);
        helper.setSubject(title);
        helper.setText(content, true); // true -> html 형식
        mailSender.send(message);
    }
}
