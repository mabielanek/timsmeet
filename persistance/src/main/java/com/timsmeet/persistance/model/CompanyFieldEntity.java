package com.timsmeet.persistance.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


/**
 * Stores information about fields for company.
 */
@Entity
@DiscriminatorValue("C")
public class CompanyFieldEntity extends FieldEntity {

  @ManyToOne
  @JoinColumn(name = "company_id", foreignKey=@ForeignKey(name="field_company_fk"))
  private CompanyEntity company;

  /**
   * Gets the company owning this field.
   *
   * @return the company
   */
  public CompanyEntity getCompany() {
    return company;
  }

  /**
   * Sets the company owning this field.
   *
   * @param company the new company
   */
  void setCompany(CompanyEntity company) {
    this.company = company;
  }

	public final static class Builder extends FieldEntity.Builder<CompanyFieldEntity> {
		public Builder() {
			super(new CompanyFieldEntity());
		}

	}

}
