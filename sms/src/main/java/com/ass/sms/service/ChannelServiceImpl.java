package com.ass.sms.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ass.sms.dto.AppResponse;
import com.ass.sms.dto.SmsDto;
import com.ass.sms.entity.SmsDetails;
import com.ass.sms.repository.ChannalRepository;

@Service
public class ChannelServiceImpl implements ChannelService {

	@Autowired
	private ChannalRepository cr;

	private AppResponse appResponseDto;

	@Override
	public AppResponse processSms(SmsDto dto) {
		// TODO Auto-generated method stub

		try {
			SmsDetails sd = new SmsDetails();
			sd.setName(dto.getName());
			sd.setContactNumber(dto.getContactNumber());
			sd.setCreatedDate(new Date());
			sd.setSmsStatus("SuCCeSs");
			sd.setSmsContact(dto.getSmsContent());

			SmsDetails savedObject = cr.save(sd);
			if (savedObject == null) {
				appResponseDto = new AppResponse("Failure", 500, null, null);
			}

			else {
				appResponseDto = new AppResponse("Success", 200, savedObject, null);
			}

		} catch (Exception e) {

			appResponseDto = new AppResponse("Failure", 200, null, e.getLocalizedMessage());

		}
		return appResponseDto;
	}

	@Override
	public List getSmsDetails(long altKey) {
		// TODO Auto-generated method stub

		return null;
	}

	public List SaveAll(List<SmsDto> smsList) {
		return null;
	}

	@Override
	public AppResponse processAllSms(List<SmsDto> dto) {
		// TODO Auto-generated method stub
		List list = new ArrayList<SmsDetails>();

		for (SmsDto sms : dto) {
			list.add(createSmsDetails(sms));

		}
		try {
			List saveAll = cr.saveAll(list);

			if (saveAll.size() == 0) {
				return new AppResponse("Failure", 500, saveAll, null);
			}
			return new AppResponse("Success", 200, saveAll, null);
		} catch (Exception e) {
			return new AppResponse("Failure", 500, null, e.getLocalizedMessage());
		}

	}

	public SmsDetails createSmsDetails(SmsDto smsDto) {
		SmsDetails smsDeatails = new SmsDetails();
		smsDeatails.setName(smsDto.getName());
		smsDeatails.setContactNumber(smsDto.getContactNumber());
		smsDeatails.setSmsContact(smsDto.getSmsContent());
		smsDeatails.setCreatedDate(new Date());
		smsDeatails.setSmsStatus("SUCCESS");

		return smsDeatails;
	}

}
