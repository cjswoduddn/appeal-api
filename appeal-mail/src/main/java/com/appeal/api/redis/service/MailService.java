package com.appeal.api.redis.service;

import com.appeal.code.ErrorCode;
import com.appeal.exception.FailSendMailException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender mailSender;
    @Value("${spring.mail.message}")
    private String targetUrl;

    public String sendVaildCodeToMember(String email, String code){
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try {
            helper.setTo(email);
            helper.setSubject("인증하세용");
            helper.setText(
                    "<a href=\""+targetUrl+"/member/"+code+"\">Cilck Me</a>"
                    , true);
            mailSender.send(message);
            return code;
        } catch (MessagingException e) {
            throw new FailSendMailException(ErrorCode.FAIL_SEND_EMAIL);
        }
    }

}
