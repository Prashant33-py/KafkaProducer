package com.apache.kafka.sample.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class ProducerService {

    private final KafkaTemplate<String, Object> template;
    @Value("${kafka.topic.name}")
    private String topicName;

    public ProducerService(KafkaTemplate<String, Object> template){
        this.template = template;
    }

    public void sendMessageToTopic(String message){
        CompletableFuture<SendResult<String, Object>> sampleTopic = template.send(topicName, message);

        sampleTopic.whenComplete((result, ex ) -> {
            if (ex == null) {
                System.out.println("Message sent successfully to topic: " + result.getRecordMetadata().topic());
            } else {
                System.out.println("Failed to send message: " + ex.getMessage());
            }

        });
    }

}