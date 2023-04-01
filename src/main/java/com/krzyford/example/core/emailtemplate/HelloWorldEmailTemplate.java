package com.krzyford.example.core.emailtemplate;

import java.util.Map;

import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import com.krzyford.example.model.ContactList;
import com.krzyford.example.model.Email;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class HelloWorldEmailTemplate implements EmailTemplate {

    private static final String FILE_NAME = "hello-world.email.template.html";

    private final SpringTemplateEngine templateEngine;

    public enum Fields {
        NAME,
        PERSONS
    }

    @Override
    public Email fill(ContactList contactList) {
        Email email = new Email();
        Context thymeleafContext = new Context();
        String emailBody = null;
        String personName = null;

        personName = contactList.getPersons().get(0).getFirstName();

        thymeleafContext.setVariables(Map.of(HelloWorldEmailTemplate.Fields.NAME.toString(), personName,
            HelloWorldEmailTemplate.Fields.PERSONS.toString(), contactList.getPersons()));
        
        emailBody = templateEngine.process(FILE_NAME, thymeleafContext);

        email.setBody(emailBody);

        return email;
    }

}
