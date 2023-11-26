package com.ass.sms.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.RestTemplate;

import com.ass.sms.SmsApplication;
import com.ass.sms.dto.AppResponse;
import com.ass.sms.entity.SmsDetails;
import com.ass.sms.repository.ChannalRepository;

@RunWith(value = PowerMockRunner.class)
@ContextConfiguration(classes = SmsApplication.class)
@SpringBootTest(classes =ChannalServiceImplTest.class )
public class ChannalServiceImplTest {
	@MockBean
	private ChannalRepository channalRepository;

	@MockBean
	private RestTemplate restTemplate;

	@MockBean
	private JdbcTemplate jdbcTemplate;

	@InjectMocks
	private ChannelServiceImpl channelServiceImpl;

	@Test
	public void testProcessChannalBySmsStatus() throws Exception {
		MockitoAnnotations.openMocks(this);
		
		SmsDetails sms=new SmsDetails();
		sms.setSmsStatus("SuCCeSs");
		
		// Creating a mock object
		List<SmsDetails> list = new ArrayList<SmsDetails>();
		list.add(sms);
		
		PowerMockito.when(channalRepository.findBySmsStatus(Mockito.eq("SuCCeSs"))).thenReturn(list);

		// Invoking the original methods from Service class
		List<SmsDetails> smsDetails = channelServiceImpl.processChannalBySmsStatus("SuCCeSs");
		
		// checking the values are equal or not
		for(int i=0;i<list.size();i++) {
			assertEquals(smsDetails.get(i).getSmsStatus(),"SuCCeSs");
			
		}
		
	}

	@Test
	public void testProcessGetAllChannalName() throws Exception {
		MockitoAnnotations.openMocks(this);
		Sort sort = Sort.by("contactNumber").descending();

		PageRequest pageRequest = PageRequest.of(0, 3, sort);
		Page<SmsDetails> findAll = channalRepository.findAll(pageRequest);
		PowerMockito.when(channalRepository, "getAllChannalName").thenReturn(findAll);

		Page<SmsDetails> page = channelServiceImpl.getAllChannalName();
		System.out.println(page+" "+findAll);
		assertEquals(findAll, page);

	}

	@Test
	public void testProcessUpdateContactNumberByAltKey() throws Exception {
		MockitoAnnotations.openMocks(this);

		long contactNumber = 0;
		long altKey = 0;

		Integer integer = new Integer((int) altKey);
		PowerMockito.when(channalRepository, "processUpdateContactNumberByAltKey", contactNumber, altKey)
				.thenReturn(integer);

		// TODO Auto-generated method stub
		// SmsDetails smsDetails=cr.processUpdateContactNumberByAltKey(contactNumber,
		// altKey);
		int value = channelServiceImpl.processUpdateContactNumberByAltKey(contactNumber, altKey);

		assertEquals(integer, value);
	}

	@Test
	public void testProcessGetChannels() throws Exception {
		MockitoAnnotations.openMocks(this);

		List<SmsDetails> testList = new ArrayList<SmsDetails>();
		PowerMockito.when(channelServiceImpl, "getChannels").thenReturn(testList);

		List<SmsDetails> list = channelServiceImpl.getChannels();

		assertEquals(testList, list);

	}

	@Test
	public void testProcessChannalByNameAndContactNumber() throws Exception {
		MockitoAnnotations.openMocks(this);

		String name = "bbbb";
		long contactNumber = 0;

		SmsDetails smsDetails = new SmsDetails();
		
		PowerMockito.when(channalRepository, "findByNameAndContactNumber", name, contactNumber).thenReturn(smsDetails);

		SmsDetails sms = channelServiceImpl.processChannalByNameAndContactNumber(name, contactNumber);

		assertEquals(smsDetails, sms);
	}

//	@Test
	public void testProcessChannalById() throws Exception {
		MockitoAnnotations.openMocks(this);

		long id = 2;
		SmsDetails testSms = new SmsDetails();
		testSms.getAltKey();
		PowerMockito.when(channelServiceImpl, "processChannalById", id).thenReturn(testSms);

		Optional<SmsDetails> sms =channalRepository.findById(id);
		SmsDetails smsGet = sms.get();

		assertEquals(testSms, smsGet);

	}
	
	
	public void testProcessSms() throws Exception {
		MockitoAnnotations.openMocks(this);
		
		SmsDetails sms=new SmsDetails();
		sms.setName(null);
		sms.setSmsContact(null);
		sms.setSmsStatus(null);
		sms.setCreatedDate(null);
		sms.setContactNumber(0);
		//Mock Object
		SmsDetails saveData= channalRepository.save(sms);
		AppResponse appResponse=new AppResponse("",0,saveData,"");
		PowerMockito.when(channelServiceImpl,"processSms",saveData).thenReturn(saveData);
	}
}