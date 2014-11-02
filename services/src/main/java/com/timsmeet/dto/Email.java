package com.timsmeet.dto;

import com.timsmeet.persistance.enums.ActivityStatus;

public class Email {
	private Long id;
	private Long lastModificationId;
	private ActivityStatus status;
	private String comment;
	private int displayIndex;
	private String emailAddress;
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
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getDisplayIndex() {
		return displayIndex;
	}
	public void setDisplayIndex(int displayIndex) {
		this.displayIndex = displayIndex;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
}
