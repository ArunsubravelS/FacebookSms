package com.ass.sms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ass.sms.constant.Constant;
import com.ass.sms.dto.AppResponse;
import com.ass.sms.dto.SmsDto;
import com.ass.sms.service.ChannelServiceImpl;

@RestController
public class ChannalController {
	
	@Autowired
	ChannelServiceImpl ch;
	
	public ChannalController() {
		System.out.println("object created");
	}
	@PostMapping(value = Constant.SendSms)
	public @ResponseBody AppResponse sendSms(@RequestBody SmsDto dto) {
		AppResponse app=ch.processSms(dto);
		return app;
	}
}
