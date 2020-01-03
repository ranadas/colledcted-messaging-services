package com.rdas;

import com.rdas.rdas.Receiver;
import com.rdas.rdas.Sender;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

//https://developer.okta.com/blog/2019/03/28/test-java-spring-boot-junit5
//https://howtodoinjava.com/spring-boot2/testing/junit5-with-spring-boot2/
@DisplayName("Basic JMS Queue tests")
@ContextConfiguration(classes = {Sender.class, Receiver.class})
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class SpringJmsApplicationTest {

    @Autowired
    private Sender sender;

    @Autowired
    private Receiver receiver;

    @DisplayName(" sending a sample test message to jms q")
    @Test
    public void testReceive() throws Exception {
        assertThat(sender).isNotNull();

//        sender.send("Hello Spring JMS ActiveMQ!");

//        receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
//        assertThat(receiver.getLatch().getCount()).isEqualTo(0);
    }
}
