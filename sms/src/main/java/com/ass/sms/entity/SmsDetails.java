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
	@Column(name="altKey")
	private int altKey;
	
	@Column(name="name")
	private String name;

	@Column(name="contactNumber")
	private long contactNumber;

	@Column(name="smsContact")
	private String smsContact;

	@Column(name="smsStatus")
	private String smsStatus;

	@Column(name="createdDate")
	private Date createdDate;

	public int getAltKey() {
		return altKey;
	}

	public void setAltKey(int altKey) {
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
