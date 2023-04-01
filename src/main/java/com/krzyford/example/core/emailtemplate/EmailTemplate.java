package com.krzyford.example.core.emailtemplate;

import com.krzyford.example.model.ContactList;
import com.krzyford.example.model.Email;

public interface EmailTemplate {
    public Email fill(ContactList contactList);
}
