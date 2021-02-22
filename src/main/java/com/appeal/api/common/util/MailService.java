package com.appeal.api.common.util;

import com.appeal.api.common.exception.SendMailFailureException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Optional;
import java.util.Random;

@RequiredArgsConstructor
@Service
public class MailService {

    private final JavaMailSender mailSender;
    private final StringRedisTemplate redisTemplate;
    private static final String FROM_ADDRESS = "fourthcow7884@gmail.com";

    public void mailSend(final String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setFrom(MailService.FROM_ADDRESS);
        message.setSubject("인증하세용");
        message.setText("일단 가는지만 테스팅 ^^");
        mailSender.send(message);
    }

    public void sendValidMail(String email, Long id){
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        String code = getRandomString(20);
        try {
            helper.setTo(email);
            helper.setSubject("인증하세용");
            helper.setText(
                    "<a href=\"http://localhost:8080/signup/"+code+
                            "\">Cilck Me</a>"
            , true);
            mailSender.send(message);
            redisTemplate.opsForValue().set(code, Long.toString(id));
        } catch (MessagingException e) {
            throw new SendMailFailureException("인증메일 전송에 실패하였습니다");
        }
    }

    public Optional<Long> getId(String code){
        String str = redisTemplate.opsForValue().get(code);
        Optional<Long> ret = Optional.ofNullable(Long.parseLong(str));
        return ret;
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
