package com.rdas.rdas;

import lombok.extern.log4j.Log4j2;
import org.springframework.jms.annotation.JmsListener;

import java.util.concurrent.CountDownLatch;

@Log4j2
public class Receiver {

    private CountDownLatch latch = new CountDownLatch(1);

    public CountDownLatch getLatch() {
        return latch;
    }

    @JmsListener(destination = "helloworld.q")
    public void receive(String message) {
        log.info("received message='{}'", message);
        latch.countDown();
    }
}
