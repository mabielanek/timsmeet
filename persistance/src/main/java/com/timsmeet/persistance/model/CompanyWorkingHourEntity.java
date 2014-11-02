package com.timsmeet.persistance.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


/**
 * Stores information about company working hour.
 */
@Entity
@DiscriminatorValue("C")
public class CompanyWorkingHourEntity extends WorkingHourEntity {
  
  @ManyToOne
  @JoinColumn(name = "company_id", foreignKey=@ForeignKey(name="wrk_hour_company_fk"))
  private CompanyEntity company;

  /**
   * Gets the company.
   *
   * @return the company
   */
  public CompanyEntity getCompany() {
    return company;
  }

  /**
   * Sets the company.
   *
   * @param company the new company
   */
  void setCompany(CompanyEntity company) {
    this.company = company;
  }
  
	public final static class Builder extends WorkingHourEntity.Builder<CompanyWorkingHourEntity> {
		public Builder() {
			super(new CompanyWorkingHourEntity());
		}

	}
  

}
