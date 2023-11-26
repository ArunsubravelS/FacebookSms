package com.ass.sms.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ass.sms.entity.SmsDetails;

public interface ChannalRepository extends JpaRepository<SmsDetails, Long> {
//for read operation we prefixed method name as find

//	@Query("from SmsDetails where smsStatus=:smsStatus")
	@Query(value = "select * from sms_details where sms_status=:smsStatus", nativeQuery = true)
	public List<SmsDetails> findBySmsStatus(String smsStatus);

//	@Query("from SmsDetails where name=:name and contactNumber=:contactNumber")
	public SmsDetails findByNameAndContactNumber(String name, long contactNumber);
	
	@Query(value="select smsStatus from SmsDetails ")
	public List<String> getAllChannalName();

	@Modifying
	@Transactional
	@Query(value="update sms_details set contact_number=:contactNumber where alt_key=:altKey",nativeQuery=true)
	public Integer processUpdateContactNumberByAltKey(@Param("contactNumber")long contactNumber,@Param("altKey")long altKey);
}	
