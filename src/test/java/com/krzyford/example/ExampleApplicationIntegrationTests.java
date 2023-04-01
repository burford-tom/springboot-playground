package com.krzyford.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import com.krzyford.example.activemq.producer.TestActiveMqProducer;

@Testcontainers
@SpringBootTest
class ExampleApplicationIntegrationTests {

    DockerImageName activeMqDockerImage = DockerImageName.parse("rmohr/activemq:latest");

    @Container
    GenericContainer<?> activeMqContainer = new GenericContainer<>(activeMqDockerImage)
        .withExposedPorts(61616);

    @Autowired
    TestActiveMqProducer testActiveMqProducer;

    @Test
    void someTest() {
        testActiveMqProducer.sendTextMessage("foobar", "Hello World!");
    }
}
