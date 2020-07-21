package com.cvc.banking_transfer.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.cvc.banking_transfer.domain.model.tax.TaxType;


@Entity
public class Transfer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String originAccount;
	private String destinationAccount;
	private BigDecimal value;
	
	private TaxType taxType;
	private BigDecimal taxValue;
	
	private OffsetDateTime openingDate;
	private OffsetDateTime scheduledDate;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getOriginAccount() {
		return originAccount;
	}
	public void setOriginAccount(String originAccount) {
		this.originAccount = originAccount;
	}
	public String getDestinationAccount() {
		return destinationAccount;
	}
	public void setDestinationAccount(String destinationAccount) {
		this.destinationAccount = destinationAccount;
	}
	public BigDecimal getValue() {
		return value;
	}
	public void setValue(BigDecimal value) {
		this.value = value;
	}
	public TaxType getTaxType() {
		return taxType;
	}
	public void setTaxType(TaxType taxType) {
		this.taxType = taxType;
		this.taxValue = this.taxType.getRule().calculate(this);
	}
	public BigDecimal getTaxValue() {
		return taxValue;
	}	
	public OffsetDateTime getOpeningDate() {
		return openingDate;
	}
	public void setOpeningDate(OffsetDateTime openingDate) {
		this.openingDate = openingDate;
	}
	public OffsetDateTime getScheduledDate() {
		return scheduledDate;
	}
	public void setScheduledDate(OffsetDateTime scheduledDate) {
		this.scheduledDate = scheduledDate;
	}
	
	
	
}
