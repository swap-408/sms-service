package com.swap.smsservice.repository;

import com.swap.smsservice.entity.Sms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SmsRepository extends JpaRepository<Sms,Long> {
    List<Sms> findByUserEmail(String userEmail);
}
