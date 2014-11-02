package com.timsmeet.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Field {

	private long id;
	private long lastModificationId;
	private BigDecimal valueDecimal;
	private long valueInteger;
	private Timestamp valueDateTime;
	private String valueString;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getLastModificationId() {
		return lastModificationId;
	}

	public void setLastModificationId(long lastModificationId) {
		this.lastModificationId = lastModificationId;
	}

	public BigDecimal getValueDecimal() {
		return valueDecimal;
	}

	public void setValueDecimal(BigDecimal valueDecimal) {
		this.valueDecimal = valueDecimal;
	}

	public long getValueInteger() {
		return valueInteger;
	}

	public void setValueInteger(long valueInteger) {
		this.valueInteger = valueInteger;
	}

	public Timestamp getValueDateTime() {
		return valueDateTime;
	}

	public void setValueDateTime(Timestamp valueDateTime) {
		this.valueDateTime = valueDateTime;
	}

	public String getValueString() {
		return valueString;
	}

	public void setValueString(String valueString) {
		this.valueString = valueString;
	}

}
