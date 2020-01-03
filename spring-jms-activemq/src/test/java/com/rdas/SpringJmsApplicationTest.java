package com.rdas;

import com.rdas.rdas.Receiver;
import com.rdas.rdas.Sender;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

//https://howtodoinjava.com/spring-boot2/testing/junit5-with-spring-boot2/
//@ExtendWith(SpringExtension.class)
@DisplayName("Basic JMS Queue tests")
@SpringBootTest
public class SpringJmsApplicationTest {

    @Autowired
    private Sender sender;

    @Autowired
    private Receiver receiver;

    @DisplayName(" sending a sample test message to jms q")
    @Test
    public void testReceive() throws Exception {
        sender.send("Hello Spring JMS ActiveMQ!");

        receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
        assertThat(receiver.getLatch().getCount()).isEqualTo(0);
    }
}
