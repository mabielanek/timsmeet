package com.timsmeet.persistance.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;


@Entity
@Table(name = "tm_employees_service_type",
  indexes={@Index(columnList="service_type_id", name="idx_est_service_fk"),
           @Index(columnList="employee_id", name="idx_est_employee_fk")}
)
public class EmployeeServiceTypeEntity {

  @Id
  @GeneratedValue(generator = "employeesServiceTypeGenerator")
  @GenericGenerator(name = "employeesServiceTypeGenerator", strategy="org.hibernate.id.enhanced.SequenceStyleGenerator", 
  parameters = { 
    @Parameter(name = "sequence_name", value="seq_tm_empl_serv_type_id")
  })
  private long id;
  
  @Version
  @Column(name = "last_modification_id")
  private long lastModificationId;

  @ManyToOne
  @JoinColumn(name = "service_type_id", nullable = false, foreignKey=@ForeignKey(name="est_service_fk"))
  private ServiceTypeEntity serviceType;

  @ManyToOne
  @JoinColumn(name = "employee_id", nullable = false, foreignKey=@ForeignKey(name="est_employee_fk"))
  private EmployeeEntity employee;

  /**
   * Gets the service type.
   *
   * @return the service type
   */
  public ServiceTypeEntity getServiceType() {
    return serviceType;
  }

  /**
   * Sets the service type.
   *
   * @param serviceType the new service type
   */
  void setServiceType(ServiceTypeEntity serviceType) {
    this.serviceType = serviceType;
  }

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

  /**
   * Gets the EmployeeServiceType record identifier.
   *
   * @return the record identifier
   */
  public long getId() {
    return id;
  }

  /**
   * Gets the last modification identifier - for optimistic concurrency locking.
   *
   * @return the last modification id
   */
  public long getLastMofidicationId() {
    return lastModificationId;
  }
  
  /**
   * Sets the last modification identifier - for optimistic concurrency locking.
   *
   * @param comment the last modification id
   */
  public void setLastModificationId(long lastModificationId) {
	  this.lastModificationId = lastModificationId;
  }
  
  public static final class Builder {
	  private EmployeeServiceTypeEntity entity = new EmployeeServiceTypeEntity();
	  
	  public EmployeeServiceTypeEntity build() {
		  return entity;
	  }
  }
 
}
