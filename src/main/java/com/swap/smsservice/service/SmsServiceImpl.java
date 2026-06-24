package com.swap.smsservice.service;

import com.swap.smsservice.dto.SmsDto;
import com.swap.smsservice.dto.SmsEventDto;
import com.swap.smsservice.entity.Sms;
import com.swap.smsservice.kafka.SmsProducer;
import com.swap.smsservice.repository.SmsRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import tools.jackson.databind.deser.CreatorProperty;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class SmsServiceImpl implements SmsService{

    private SmsRepository repository;
    private final SmsProducer smsProducer;
    private final SmsDedupeService smsDedupeService;

    @Override
    public SmsDto createSms(SmsDto smsDto) {
        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        if (smsDedupeService.isDuplicate(smsDto.getMessage())) {
            return smsDto;
        }

        Sms sms = new Sms();
        sms.setUserEmail(email);
        sms.setMessage(smsDto.getMessage());
        sms.setReceivedAt(LocalDateTime.now());

        Sms saved = repository.save(sms);

        smsProducer.publishSms(
                new SmsEventDto(saved.getUserEmail(), saved.getMessage())
        );

        return toDto(saved);
    }

    @Override
    public List<SmsDto> getAllSms() {
        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        return repository.findByUserEmail(email)
                .stream()
                .map(this::toDto)
                .toList();
    }

    private SmsDto toDto(Sms sms) {

        SmsDto dto = new SmsDto();
        dto.setId(sms.getId());
        dto.setMessage(sms.getMessage());
        dto.setReceivedAt(sms.getReceivedAt());

        return dto;

    }
}
