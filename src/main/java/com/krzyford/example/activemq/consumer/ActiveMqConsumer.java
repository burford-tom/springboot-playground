package com.krzyford.example.activemq.consumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import jakarta.jms.JMSException;
import jakarta.jms.TextMessage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ActiveMqConsumer {

    @JmsListener(destination = "${spring.activemq.consumer.destination}")
    public void sampleJmsListenerMethod(TextMessage message) throws JMSException {
        log.info("JMS listener received text message: {}", message.getText());
    }
}
