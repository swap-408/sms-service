package com.swap.smsservice.service;

import com.swap.smsservice.dto.SmsDto;
import com.swap.smsservice.entity.Sms;
import org.springframework.stereotype.Service;

import java.util.List;

public interface SmsService {
    SmsDto createSms(SmsDto smsDto);
    List<SmsDto> getAllSms();
}
