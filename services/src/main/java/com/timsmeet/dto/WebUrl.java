package com.timsmeet.dto;

import com.timsmeet.persistance.enums.ActivityStatus;

public class WebUrl {

	private Long id;
	private Long lastModificationId;
	private ActivityStatus status;
	private String comment;
	private Integer displayIndex;
	private String webUrlAddress;

	public long getId() {
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

	public Integer getDisplayIndex() {
		return displayIndex;
	}

	public void setDisplayIndex(Integer displayIndex) {
		this.displayIndex = displayIndex;
	}

	public String getWebUrlAddress() {
		return webUrlAddress;
	}

	public void setWebUrlAddress(String webUrlAddress) {
		this.webUrlAddress = webUrlAddress;
	}
}
