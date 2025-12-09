package com.apache.kafka.sample.controllers;

import com.apache.kafka.sample.service.ProducerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/producer")
public class ProducerController {

    private final ProducerService producerService;

    public ProducerController(ProducerService producerService) {
        this.producerService = producerService;
    }

    @GetMapping("/publish/{message}")
    public ResponseEntity<String> produceMessage(@PathVariable String message) {
        try {
            for (int i = 0; i < 15000; i++) {
                String msg = message + " - " + i;
                producerService.sendMessageToTopic(msg);
            }
            return ResponseEntity.ok().body("Message published successfully");
        } catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

}
