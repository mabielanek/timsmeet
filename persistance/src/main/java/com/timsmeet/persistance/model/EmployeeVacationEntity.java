package com.timsmeet.persistance.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
@DiscriminatorValue("E")
public class EmployeeVacationEntity extends VacationEntity {

  @ManyToOne
  @JoinColumn(name = "employee_id", foreignKey=@ForeignKey(name="vacation_employee_fk"))
  private EmployeeEntity employee;

  /**
   * Gets the employee.
   *
   * @return the employee
   */
  public EmployeeEntity getEmployee() {
    return employee;
  }

  /**
   * Sets the employee.
   *
   * @param employee the new employee
   */
  void setEmployee(EmployeeEntity employee) {
    this.employee = employee;
  }

	public final static class Builder extends VacationEntity.Builder<EmployeeVacationEntity> {
		public Builder() {
			super(new EmployeeVacationEntity());
		}

	}

  
}
