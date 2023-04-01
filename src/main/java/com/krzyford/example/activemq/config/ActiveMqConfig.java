package com.krzyford.example.activemq.config;

import java.util.List;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.jms.ConnectionFactory;
import lombok.Data;

@Configuration
@ConfigurationProperties(prefix = "spring.activemq")
@Data
public class ActiveMqConfig {

    private String brokerUrl;
    private String user;
    private String password;
    private List<String> packagesTrusted;

    @Bean
    ConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(user, password, brokerUrl);
        connectionFactory.setTrustedPackages(packagesTrusted);
        return connectionFactory;
    }
}
