package com.swap.smsservice.controller;

import com.swap.smsservice.dto.SmsDto;
import com.swap.smsservice.service.SmsService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sms")
@AllArgsConstructor
public class SmsController {

    private final SmsService smsService;

    @PostMapping
    public SmsDto createSms(@Valid @RequestBody SmsDto smsDto) {
        return smsService.createSms(smsDto);
    }

    @GetMapping
    public List<SmsDto> getAllSms() {
        return smsService.getAllSms();
    }


}
