package com.rdas.api;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
public class ReportController {

    @GetMapping(value = "/status")
    public ResponseEntity<?> reportStatus() {
        return null;
    }
}
