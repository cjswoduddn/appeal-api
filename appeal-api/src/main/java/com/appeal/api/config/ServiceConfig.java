package com.appeal.api.config;

import com.appeal.service.AwsS3Service;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Bean
    public AwsS3Service awsS3Service(){
        return new AwsS3Service();
    }

//    @Bean
//    public MailService mailService(){
//        return new MailService();
//    }
}
