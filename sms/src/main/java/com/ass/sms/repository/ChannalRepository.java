package com.ass.sms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ass.sms.entity.SmsDetails;

public interface ChannalRepository extends JpaRepository<SmsDetails, Long>{

}
