package com.apache.kafka.sample.service;

import com.apache.kafka.sample.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    Logger logger = LoggerFactory.getLogger(ProducerService.class);

    public ProducerService(KafkaTemplate<String, Object> template){
        this.template = template;
    }

    public void sendMessageToTopic(String message){
        CompletableFuture<SendResult<String, Object>> sampleTopic = template.send(topicName, message);

        sampleTopic.whenComplete((result, ex ) -> {
            if (ex == null) {
                logger.info("Message sent successfully to topic: {}", result.getRecordMetadata().topic());
            } else {
                logger.error("Failed to send message: {}", ex.getMessage());
            }

        });
    }

    /**
     * This method sends Customer object to Kafka topic. This is for practise only
     * @param customer Customer object to be sent to topic
     */
    public void sendCustomerToTopic(Customer customer){
        CompletableFuture<SendResult<String, Object>> sampleTopic = template.send(topicName, customer);
        sampleTopic.whenComplete((result, ex ) -> {
            if (ex == null) {
                logger.info("Customer object {} sent", customer);
                logger.info("Customer sent successfully to topic: {}", result.getRecordMetadata().topic());
            } else {
                logger.error("Failed to send customer: {}", ex.getMessage());
            }

        });
    }

}