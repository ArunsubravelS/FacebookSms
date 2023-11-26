package com.ass.sms.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="sms_details")
public class SmsDetails {
	
	@Id
	@GenericGenerator(name="auto",strategy = "increment")
	@GeneratedValue(generator ="auto")
	@Column(name="alt_key")
	private long altKey;
	
	@Column(name="name")
	private String name;

	@Column(name="contact_number")
	private long contactNumber;

	@Column(name="sms_contact")
	private String smsContact;

	@Column(name="sms_status")
	private String smsStatus;

	@Column(name="created_date")
	private Date createdDate;

	public long getAltKey() {
		return altKey;
	}

	public void setAltKey(long altKey) {
		this.altKey = altKey;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(long contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getSmsContact() {
		return smsContact;
	}

	public void setSmsContact(String string) {
		this.smsContact = string;
	}

	public String getSmsStatus() {
		return smsStatus;
	}

	public void setSmsStatus(String smsStatus) {
		this.smsStatus = smsStatus;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	@Override
	public String toString() {
		return "SmsDetails [altKey=" + altKey + ", name=" + name + ", contactNumber=" + contactNumber + ", smsContact="
				+ smsContact + ", smsStatus=" + smsStatus + ", createdDate=" + createdDate + "]";
	}


}
