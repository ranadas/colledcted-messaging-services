package com.rdas.api.resource;

import com.rdas.api.service.MessageService;
import com.rdas.common.message.model.ControlMessage;
import com.rdas.common.message.model.StepDefinitionMark;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

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

    //curl -X POST localhost:8081/rpt/start?name=rdas
    @PostMapping("/start")
    public ResponseEntity<?> startProcess(@RequestParam String name) {
        ControlMessage controlMessage = new ControlMessage(UUID.randomUUID().toString(), StepDefinitionMark.ACCEPTED);
        String response = messageService.startProcess(controlMessage);
        return ResponseEntity.ok(response);
    }
}
