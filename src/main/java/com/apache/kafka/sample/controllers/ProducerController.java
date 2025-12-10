package com.apache.kafka.sample.controllers;

import com.apache.kafka.sample.model.Customer;
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

    @PostMapping("/publish/customer")
    public ResponseEntity<String> produceCustomer(/*@RequestBody Customer customer*/){
        try {
            producerService.sendCustomerToTopic(Customer.builder().id("1").name("abc").email("abc@email.com").contactNo("12345678").build());
            return ResponseEntity.ok().body("Customer published successfully");
        } catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

}
