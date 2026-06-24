package com.swap.smsservice.service;

public interface SmsDedupeService {
    boolean isDuplicate(String message);
}
