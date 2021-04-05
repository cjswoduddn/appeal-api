package com.appeal.service;

import com.appeal.exception.ErrorCode;
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

    public String sendVaildCodeToMember(String email){
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        String code = getRandomString(20);
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

    private String getRandomString( int length ){
        char[] charaters = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q',
                'r','s','t','u','v','w','x','y','z','0','1','2','3','4','5','6','7','8','9'};
        StringBuffer sb = new StringBuffer();
        Random rn = new Random();
        for( int i = 0 ; i < length ; i++ ){
            sb.append( charaters[ rn.nextInt( charaters.length ) ] );
        }
        return sb.toString();
    }
}
