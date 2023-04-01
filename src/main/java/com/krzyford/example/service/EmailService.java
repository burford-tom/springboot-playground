package com.krzyford.example.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.krzyford.example.config.EmailConfig;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final EmailConfig config;

    private final JavaMailSender javaMailSender;

    public void sendEmail(String to, String subject, String contents) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom(config.getUsername());
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(contents);

        javaMailSender.send(mailMessage);
    }
}
