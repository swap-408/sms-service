package com.swap.smsservice.kafka;

import com.swap.smsservice.dto.SmsDto;
import com.swap.smsservice.dto.SmsEventDto;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class SmsProducer {

    private final KafkaTemplate<String, SmsEventDto> kafkaTemplate;

    public SmsProducer(KafkaTemplate<String, SmsEventDto> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishSms(SmsEventDto event) {

        kafkaTemplate.send("sms.received", event);

    }
}