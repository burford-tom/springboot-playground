package com.krzyford.example.factory;

import org.springframework.stereotype.Component;

import com.krzyford.example.core.EmailType;
import com.krzyford.example.core.emailtemplate.HelloWorldEmailTemplate;
import com.krzyford.example.core.emailtemplate.EmailTemplate;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EmailTemplateFactory {

    private final HelloWorldEmailTemplate helloWorldEmailTemplate;

    public EmailTemplate getEmailTemplate(EmailType emailType) {
        EmailTemplate emailTemplate = null;

        switch (emailType) {
            case HELLOWORLD:
                emailTemplate = helloWorldEmailTemplate;
                break;
            default:
                throw new UnsupportedOperationException("Email Type is not supported");
        }

        return emailTemplate;
    }
}
