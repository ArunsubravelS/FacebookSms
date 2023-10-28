package com.ass.sms.dto;

import java.io.Serializable;

public class AppResponse implements Serializable {

	private String status;

	private int statusCode;

	private Object data;

	private String error;

	public AppResponse(String status, int i, Object data, String error) {
		super();
		this.status = status;
		this.statusCode = i;
		this.data = data;
		this.error = error;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

}
