package com.krzyford.example.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.krzyford.example.core.EmailType;
import com.krzyford.example.core.emailtemplate.EmailTemplate;
import com.krzyford.example.factory.EmailTemplateFactory;
import com.krzyford.example.model.ContactList;
import com.krzyford.example.model.Email;
import com.krzyford.example.repository.ContactListRepository;

public class EmailService {

    @Autowired
    ContactListRepository contactListRepository;

    @Autowired
    EmailTemplateFactory emailTemplateFactory;

    public Email create(EmailType actionType) {
        Email email = null;
        EmailTemplate emailTemplate = null;
        ContactList contactList = null;
        
        contactList = contactListRepository.read();

        emailTemplate = emailTemplateFactory.getEmailTemplate(actionType);

        email = emailTemplate.fill(contactList);

        return email;
    }
}
