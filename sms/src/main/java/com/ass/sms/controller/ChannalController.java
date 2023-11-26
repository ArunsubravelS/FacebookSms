 package com.ass.sms.controller;

import java.util.List;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ass.sms.constant.Constant;
import com.ass.sms.dto.AppResponse;
import com.ass.sms.dto.SmsDto;
import com.ass.sms.entity.SmsDetails;
import com.ass.sms.service.ChannelServiceImpl;

@RestController
public class ChannalController {
	
	@Autowired
	ChannelServiceImpl ch;
	
	Logger logger=LoggerFactory.getLogger(ChannalController.class);
	
	public ChannalController() {
		System.out.println("object created");
	}
	@PostMapping(value = Constant.SendSms)
	public @ResponseBody AppResponse sendSms(@RequestBody SmsDto dto) {
		AppResponse app=ch.processSms(dto);
		logger.info("sendSms() method is executed");
		return app;
	}
	@GetMapping(value = Constant.Get_SmsStatus)
	public @ResponseBody List<SmsDetails> getChannalBySmsStatus(@RequestParam("smsStatus") String smsStatus) {
		return ch.processChannalBySmsStatus(smsStatus);
	}
	
	@GetMapping(value = Constant.Get_Name_Cnumber)
	public  @ResponseBody SmsDetails getChannalByNameAndContactNumber(@RequestHeader("name") String name,@RequestHeader("ContactNumber")long ContactNumber) {
		return ch.processChannalByNameAndContactNumber(name, ContactNumber);
	}
	@PutMapping(value=Constant.Update_ContactNumber_By_AltKey)
	public @ResponseBody Integer processUpdateContactNumberByAltKey(@PathVariable("contactNumber")long contactNumber,@PathVariable("altKey")long altKey){
	
	return ch.processUpdateContactNumberByAltKey(contactNumber, altKey);
		
	}
	@GetMapping(value=Constant.getChannels)
	public @ResponseBody List<SmsDetails> getAllChannals() {
	return ch.getChannels();
	}
}
