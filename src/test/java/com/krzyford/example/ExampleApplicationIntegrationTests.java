package com.krzyford.example;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.MailSendException;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

import com.icegreen.greenmail.configuration.GreenMailConfiguration;
import com.icegreen.greenmail.junit5.GreenMailExtension;
import com.icegreen.greenmail.util.ServerSetupTest;
import com.krzyford.example.service.EmailService;

import jakarta.mail.internet.MimeMessage;

@SpringBootTest
class ExampleApplicationIntegrationTests {

    @Autowired
    EmailService classUnderTest;

    static final GreenMailConfiguration mockSmtpConfig = GreenMailConfiguration.aConfig()
        .withUser("no-reply@krzyford.com", "blah");

    @RegisterExtension
    static final GreenMailExtension mockSmtp = new GreenMailExtension(ServerSetupTest.SMTP)
        .withConfiguration(mockSmtpConfig);

    @DynamicPropertySource
    static void registerProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.mail.host", () -> "127.0.0.1");
        registry.add("spring.mail.port", () -> "3025");
    }

    @Test
    void givenSmtpServerIsUpThenEmailIsSent() throws Exception {
        List<MimeMessage> valueUnderTest = null;

        classUnderTest.sendEmail("test@krzyford.com", "Hello World", "Hello World!");

        valueUnderTest = List.of(mockSmtp.getReceivedMessages());

        assertNotNull(valueUnderTest);

        assertEquals(1, valueUnderTest.size());

        assertEquals("Hello World!", valueUnderTest.get(0)
            .getContent()
            .toString());
    }

    @Test
    void givenSmtpServerIsDownThenExceptionIsThrown() {
        mockSmtp.stop();

        assertThrows(MailSendException.class, () -> {
            classUnderTest.sendEmail("test@krzyford.com", "Hello World", "Hello World!");
        });
    }
}
