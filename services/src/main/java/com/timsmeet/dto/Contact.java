package com.timsmeet.dto;

import java.util.ArrayList;
import java.util.List;

import com.timsmeet.persistance.enums.ActivityStatus;

public class Contact {
	private Long id;
	private Long lastModificationId;
	private ActivityStatus status;
	private List<Phone> phones = new ArrayList<Phone>();
	private List<WebUrl> webUrls = new ArrayList<WebUrl>();
	private List<Email> emails = new ArrayList<Email>();

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

	public List<Phone> getPhones() {
		return phones;
	}

	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}

	public List<WebUrl> getWebUrls() {
		return webUrls;
	}

	public void setWebUrls(List<WebUrl> webUrls) {
		this.webUrls = webUrls;
	}

	public List<Email> getEmails() {
		return emails;
	}

	public void setEmails(List<Email> emails) {
		this.emails = emails;
	}

}
