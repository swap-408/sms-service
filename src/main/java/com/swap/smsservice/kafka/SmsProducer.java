package com.swap.smsservice.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class SmsProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public SmsProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishSms(String message) {
        kafkaTemplate.send("sms.received", message);
    }
}