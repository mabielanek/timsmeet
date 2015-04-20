package com.timsmeet.dto;

import java.sql.Timestamp;

import com.timsmeet.dto.entity.BaseEntity;
import com.timsmeet.dto.entity.EntityState;

public class Vacation extends BaseEntity {

	private Long id;
	private Long lastModificationId;
	private Timestamp startDay;
	private Timestamp endDay;

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

	public Timestamp getStartDay() {
		return startDay;
	}

	public void setStartDay(Timestamp startDay) {
		this.startDay = startDay;
	}

	public Timestamp getEndDay() {
		return endDay;
	}

	public void setEndDay(Timestamp endDay) {
		this.endDay = endDay;
	}

	public static final class Builder {
		private final Vacation vacation = new Vacation();

		public Builder(EntityState entityState, Timestamp startDay, Timestamp endDay) {
			vacation.getEntityAspect().setEntityState(entityState);
			vacation.setStartDay(startDay);
			vacation.setEndDay(endDay);
		}

		public Vacation build() {
			return vacation;
		}

		public Builder id(Long id) {
			vacation.setId(id);
			return this;
		}

		public Builder lastModificationId(Long lastModificationId) {
			vacation.setLastModificationId(lastModificationId);
			return this;
		}
		
		public Builder entityState(EntityState entityState) {
			vacation.getEntityAspect().setEntityState(entityState);
			return this;
		}

		public Builder startDay(Timestamp startDay) {
			vacation.setStartDay(startDay);
			return this;
		}

		public Builder endDay(Timestamp endDay) {
			vacation.setEndDay(endDay);
			return this;
		}
	}
}
