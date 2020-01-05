package com.rdas.api.resource;

import com.rdas.api.service.MessageService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;
import com.rdas.common.message.model.ControlMessage;

@Log4j2
@RestController
public class ReportController {

    private final MessageService messageService;

    public ReportController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping(value = "/status")
    public ResponseEntity<?> reportStatus() {
        return ResponseEntity.ok("WIP");
    }

    @PostMapping("/start")
    public ResponseEntity<?> startProcess(@RequestParam String name) throws NoSuchAlgorithmException, UnsupportedEncodingException {

        ControlMessage controlMessage = ControlMessage.builder()
                .uuid(UUID.randomUUID().toString())
                .name(name)
                .build();
        String response = messageService.startProcess(controlMessage);
        return ResponseEntity.ok(response);
    }

}
