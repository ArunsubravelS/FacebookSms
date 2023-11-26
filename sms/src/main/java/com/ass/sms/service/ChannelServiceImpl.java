package com.ass.sms.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.ass.sms.dto.AppResponse;
import com.ass.sms.dto.SmsDto;
import com.ass.sms.entity.SmsDetails;
import com.ass.sms.repository.ChannalRepository;

@Service
public class ChannelServiceImpl implements ChannelService {

	@Autowired
	private ChannalRepository cr;
	
	@Autowired
	JdbcTemplate jdbcTemplate; 

	private AppResponse appResponseDto;

	@Override
	public AppResponse processSms(SmsDto dto) {
		// TODO Auto-generated method stub

		try {
			SmsDetails sd = new SmsDetails();
			sd.setName(dto.getName());
			sd.setContactNumber(dto.getContactNumber());
			sd.setCreatedDate(new Date());
			sd.setSmsStatus("SUCCESS");
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
		
		List list=new ArrayList<SmsDetails>();
		list.add(smsList);
		
		
		
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

	@Override
	public SmsDetails processChannalById(long id) {
		// TODO Auto-generated method stub
  
		Optional<SmsDetails> optional = cr.findById(id);
		SmsDetails smsDetails = optional.get();
		return smsDetails;
	}

	@Override
	public List<SmsDetails> processChannalBySmsStatus(String smsStatus) {
		// TODO Auto-generated method stub
		
		List<SmsDetails> list=cr.findBySmsStatus(smsStatus);
		return list;
		
	}

	@Override
	public SmsDetails processChannalByNameAndContactNumber(String name, long ContactNumber) {
		// TODO Auto-generated method stub
		SmsDetails smsDetails=cr.findByNameAndContactNumber(name, ContactNumber);
		return smsDetails;
	}
	
	@Override
	public Page<SmsDetails> getAllChannalName(){
		Sort sort=Sort.by("contactNumber").descending();
		//PageRequest pageRequest=PageRequest.of(0, 3);
		PageRequest pageRequest=PageRequest.of(0, 3,sort);
		Page<SmsDetails> findAll=cr.findAll(pageRequest);
		//List<SmsDetails> collect=findAll.get().collect(Collector<T, A, R>)
		return cr.findAll(pageRequest);
		
	}

	@Override
	public Integer processUpdateContactNumberByAltKey(long contactNumber,long altKey) {
		// TODO Auto-generated method stub
		//SmsDetails smsDetails=cr.processUpdateContactNumberByAltKey(contactNumber, altKey);
		return cr.processUpdateContactNumberByAltKey(contactNumber, altKey);
	}

	@Override
	public List<SmsDetails> getChannels() {
		// TODO Auto-generated method stub	
		StringBuilder builder=new StringBuilder();
		builder.append("select * from sms_details");
		List<Map<String,Object>> resultList=
				jdbcTemplate.queryForList(builder.toString());
		List<SmsDetails> collect = resultList.stream().map(each	->{
			SmsDetails smsDetails=new SmsDetails();
			smsDetails.setAltKey(Long.parseLong(each.get("alt_key").toString()));
			smsDetails.setContactNumber(Long.parseLong(each.get("contact_number").toString()));
			smsDetails.setName(each.get("name").toString());
			return smsDetails;
		}).collect(Collectors.toList());
		return collect;
	}

}
