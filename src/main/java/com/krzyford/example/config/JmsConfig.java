package com.krzyford.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

import jakarta.jms.ConnectionFactory;
import lombok.RequiredArgsConstructor;

@EnableJms
@Configuration
@RequiredArgsConstructor
public class JmsConfig {

    private final ConnectionFactory connectionFactory;

    @Bean
    DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        return factory;
    }

    @Bean
    JmsTemplate jmsTemplate() {
        return new JmsTemplate(connectionFactory);
    }
}
