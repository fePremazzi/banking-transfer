package com.cvc.banking_transfer.api.exceptionhandler;

import java.time.OffsetDateTime;

public class ResponseException {

	private Integer status;
	private OffsetDateTime dateTime;
	private String description;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public OffsetDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(OffsetDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
