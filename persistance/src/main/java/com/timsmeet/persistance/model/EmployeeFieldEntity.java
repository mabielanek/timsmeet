package com.timsmeet.persistance.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
@DiscriminatorValue("E")
public class EmployeeFieldEntity extends FieldEntity {

  @ManyToOne
  @JoinColumn(name = "employee_id", foreignKey=@ForeignKey(name="field_employee_fk"))
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
   * @param employee the new employee entity
   */
  void setEmployee(EmployeeEntity employee) {
    this.employee = employee;
  }

	public final static class Builder extends FieldEntity.Builder<EmployeeFieldEntity> {
		public Builder() {
			super(new EmployeeFieldEntity());
		}

	}

}
