package com.timsmeet.dto;

import com.timsmeet.persistance.enums.ActivityStatus;
import com.timsmeet.persistance.enums.PhoneNumberType;

public class Phone {
	private Long id;
	private Long lastModificationId;
	private ActivityStatus status;
	private String phone;
	private String phoneExt;
	private PhoneNumberType numberType;
	private String comment;
	private Integer displayIndex;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getLastModificationId() {
		return lastModificationId;
	}
	public void setLastModificationId(Long lastModificationId) {
		this.lastModificationId = lastModificationId;
	}
	public ActivityStatus getStatus() {
		return status;
	}
	public void setStatus(ActivityStatus status) {
		this.status = status;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPhoneExt() {
		return phoneExt;
	}
	public void setPhoneExt(String phoneExt) {
		this.phoneExt = phoneExt;
	}
	public PhoneNumberType getNumberType() {
		return numberType;
	}
	public void setNumberType(PhoneNumberType numberType) {
		this.numberType = numberType;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Integer getDisplayIndex() {
		return displayIndex;
	}
	public void setDisplayIndex(Integer displayIndex) {
		this.displayIndex = displayIndex;
	}
}
