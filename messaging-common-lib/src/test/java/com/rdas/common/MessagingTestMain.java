package com.rdas.common;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootApplication
public class MessagingTestMain {
    public static void main(String[] args) {
        SpringApplication.run(MessagingTestMain.class, args);
    }
}
