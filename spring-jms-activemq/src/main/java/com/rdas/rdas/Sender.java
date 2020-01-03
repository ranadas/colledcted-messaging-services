package com.rdas.rdas;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;

@Log4j2
public class Sender {

    @Autowired
    private JmsTemplate jmsTemplate;

    public void send(String message) {
        log.info("sending message='{}'", message);
        jmsTemplate.convertAndSend("helloworld.q", message);
    }
}
