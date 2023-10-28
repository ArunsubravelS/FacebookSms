package com.ass.sms.service;

import java.util.List;

import com.ass.sms.dto.AppResponse;
import com.ass.sms.dto.SmsDto;

public interface ChannelService {
	
	
	public AppResponse processSms(SmsDto dto);
	public AppResponse processAllSms(List<SmsDto> dto);
	public  List getSmsDetails(long altKey);

}
