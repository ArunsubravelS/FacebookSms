package com.ass.sms.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.ass.sms.dto.AppResponse;
import com.ass.sms.dto.SmsDto;
import com.ass.sms.entity.SmsDetails;

public interface ChannelService {
	
	
	public AppResponse processSms(SmsDto dto);
	public AppResponse processAllSms(List<SmsDto> dto);
	public  List getSmsDetails(long altKey);
	public SmsDetails processChannalById(long id);
	public List<SmsDetails> processChannalBySmsStatus(String smsStatus);
	public SmsDetails processChannalByNameAndContactNumber(String name,long ContactNumber);
	public Page<SmsDetails> getAllChannalName();
	public Integer processUpdateContactNumberByAltKey(long contactNumber,long altKey);
	public List<SmsDetails> getChannels();
}
