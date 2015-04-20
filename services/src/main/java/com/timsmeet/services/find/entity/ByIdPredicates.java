package com.timsmeet.services.find.entity;

import com.google.common.base.Predicate;
import com.timsmeet.persistance.model.CompanyVacationEntity;

public class ByIdPredicates {
	
	
	public static abstract class ByIdHelper<T> implements Predicate<T> {
		private long comparedId;
		
		public long getComparedId() {
			return comparedId;
		}

		public void setComparedId(long comparedId) {
			this.comparedId = comparedId;
		}
		
		
	}

	public static class CompanyVacationEntityByIdPredicate extends ByIdHelper<CompanyVacationEntity> {

		@Override
		public boolean apply(CompanyVacationEntity input) {
			return input.getId() == getComparedId();
		}
		
	}
}
