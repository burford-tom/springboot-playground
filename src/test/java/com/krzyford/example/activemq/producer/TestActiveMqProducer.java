package com.krzyford.example.activemq.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TestActiveMqProducer {

    @Autowired
    private JmsTemplate jmsTemplate;

    public void sendTextMessage(String destination, String message) {
        log.info("Sending message to {} destination with text {}", destination, message);
        jmsTemplate.send(destination, s -> s.createTextMessage(message));
    }
}
