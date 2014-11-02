/*
 * 
 */
package com.timsmeet.persistance.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.timsmeet.persistance.enums.ActivityStatus;
//import org.hibernate.annotations.ForeignKey;

/**
 * Stores information about company.
 */
@Entity
@Table(name = "tm_company", 
  indexes = {@Index(columnList="name", name="idx_company_name"),
             @Index(columnList="address_id", name="idx_company_address_fk"),
             @Index(columnList="contact_id", name="idx_company_contact_fk")}
)
public class CompanyEntity {

  @Id
  @GeneratedValue(generator = "companyGenerator")
  @GenericGenerator(name = "companyGenerator", strategy="org.hibernate.id.enhanced.SequenceStyleGenerator", 
  parameters = { 
    @Parameter(name = "sequence_name", value="seq_tm_company_id"),
    @Parameter(name = "initial_value", value="100")
  })
  private long id;
  
  @Version
  @Column(name = "last_modification_id")
  private long lastModificationId;
  
  @Column(name = "status", nullable = false, length = 1)
  @org.hibernate.annotations.Check(constraints = "status IN('A','I','D')")
  private String status;
  
  @Column(name = "name", nullable = false, length = 255)
  private String name;
  
  @OneToOne(cascade = {CascadeType.ALL})
  @JoinColumn(name = "address_id", foreignKey=@ForeignKey(name="company_address_fk"))
  private AddressEntity address;
  
  @OneToOne(cascade = {CascadeType.ALL})
  @JoinColumn(name = "contact_id", foreignKey=@ForeignKey(name="company_contact_fk"))
  private ContactEntity contact;
  
  @OneToMany(cascade = {CascadeType.ALL})
  @JoinColumn(name = "company_id")
  private List<ServiceTypeEntity> serviceTypes = new ArrayList<ServiceTypeEntity>();
  
  @OneToMany(cascade = {CascadeType.ALL})
  @JoinColumn(name = "company_id")
  private List<ServiceLocationEntity> serviceLocations = new ArrayList<ServiceLocationEntity>();
  
  @OneToMany(cascade = {CascadeType.ALL})
  @JoinColumn(name = "company_id")
  private List<CompanyWorkingHourEntity> workingHours = new ArrayList<CompanyWorkingHourEntity>();
  
  @OneToMany(cascade = {CascadeType.ALL})
  @JoinColumn(name = "company_id")
  private List<CompanyVacationEntity> vacations = new ArrayList<CompanyVacationEntity>();
  
  @OneToMany(cascade = {CascadeType.ALL})
  @JoinColumn(name = "company_id")
  private List<CompanyFieldEntity> fields = new ArrayList<CompanyFieldEntity>();

  /**
   * Gets the record status.
   *
   * @return the status
   */
  public ActivityStatus getStatus() {
    return ActivityStatus.forCode(status);
  }

  /**
   * Sets the record status.
   *
   * @param status the new status
   */
  public void setStatus(ActivityStatus status) {
    this.status = status.getCode();
  }

  /**
   * Gets the name of the company/
   *
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the name of the company.
   *
   * @param name the new name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Gets the address of the company.
   *
   * @return the address
   */
  public AddressEntity getAddress() {
    return address;
  }

  /**
   * Sets the address of the company.
   *
   * @param address the new address
   */
  public void setAddress(AddressEntity address) {
    this.address = address;
  }

  /**
   * Gets the contact for the company.
   *
   * @return the contact
   */
  public ContactEntity getContact() {
    return contact;
  }

  /**
   * Sets the contact for the company.
   *
   * @param contact the new contact
   */
  public void setContact(ContactEntity contact) {
    this.contact = contact;
  }
  
  /**
   * Gets the service types.
   *
   * @return the service types
   */
  public List<ServiceTypeEntity> getServiceTypes() {
    return serviceTypes;
  }
  
  /**
   * Adds the service type.
   *
   * @param serviceType the service type
   * @return true, if collection changed as a result of the call
   */
  public boolean addServiceType(ServiceTypeEntity serviceType) {
    if(this.serviceTypes == null) {
      this.serviceTypes = Lists.newArrayList();
    }
    serviceType.setCompany(this);
    return this.serviceTypes.add(serviceType);
  }
  
  /**
   * Removes the service type.
   *
   * @param serviceType the service type
   * @return true, if list contained the specified element
   */
  public boolean removeServiceType(ServiceTypeEntity serviceType) {
    Preconditions.checkNotNull(serviceType);
    serviceType.setCompany(null);
    if(this.serviceTypes != null) {
      return this.serviceTypes.remove(serviceType);
    }
    return false;
  }
  
  /**
   * Gets the service locations.
   *
   * @return the service locations
   */
  public List<ServiceLocationEntity> getServiceLocations() {
    return serviceLocations;
  }
  
  /**
   * Adds the service location.
   *
   * @param serviceLocation the service location
   * @return true, if collection changed as a result of the call
   */
  public boolean addServiceLocation(ServiceLocationEntity serviceLocation) {
    if(this.serviceLocations == null) {
      this.serviceLocations = Lists.newArrayList();
    }
    serviceLocation.setCompany(this);
    return this.serviceLocations.add(serviceLocation);
  }
  
  /**
   * Removes the service location.
   *
   * @param serviceLocation the service location
   * @return true, if list contained the specified element
   */
  public boolean removeServiceLocation(ServiceLocationEntity serviceLocation) {
    Preconditions.checkNotNull(serviceLocation);
    serviceLocation.setCompany(null);
    if (this.serviceLocations != null) {
      return this.serviceLocations.remove(serviceLocation);
    }
    return false;
  }

  /**
   * Gets the working hours.
   *
   * @return the working hours
   */
  public List<CompanyWorkingHourEntity> getWorkingHours() {
    return workingHours;
  }
  
  /**
   * Adds the working hour.
   *
   * @param workingHour the working hour
   * @return true, if collection changed as a result of the call
   */
  public boolean addWorkingHour(CompanyWorkingHourEntity workingHour) {
    if(this.workingHours == null) {
      this.workingHours = Lists.newArrayList();
    }
    workingHour.setCompany(this);
    return this.workingHours.add(workingHour);
  }
  
  /**
   * Removes the working hour.
   *
   * @param workingHour the working hour
   * @return true, if list contained the specified element
   */
  public boolean removeWorkingHour(CompanyWorkingHourEntity workingHour) {
    Preconditions.checkNotNull(workingHour);
    workingHour.setCompany(null);
    if(this.workingHours != null) {
      return this.workingHours.remove(workingHour);
    }
    return false;
  }
  
  /**
   * Gets the vacations.
   *
   * @return the vacations
   */
  public List<CompanyVacationEntity> getVacations() {
    return vacations;
  }
  
  /**
   * Adds the vacation.
   *
   * @param vacation the company vacation
   * @return true, if collection changed as a result of the call
   */
  public boolean addVacation(CompanyVacationEntity vacation) {
    if(this.vacations == null) {
      this.vacations = Lists.newArrayList();
    }
    vacation.setCompany(this);
    return this.vacations.add(vacation);
  }

  /**
   * Removes the vacation.
   *
   * @param vacation the vacation
   * @return true, if list contained the specified element
   */
  public boolean removeVacation(CompanyVacationEntity vacation) {
    Preconditions.checkNotNull(vacation);
    vacation.setCompany(null);
    if(this.vacations != null) {
      return this.vacations.remove(vacation);
    }
    return false;
  }
  
  /**
   * Gets the fields.
   *
   * @return the fields
   */
  public List<CompanyFieldEntity> getFields() {
    return fields;
  }
  
  /**
   * Adds the field.
   *
   * @param field the field
   * @return true, if collection changed as a result of the call
   */
  public boolean addField(CompanyFieldEntity field) {
    if (this.fields == null) {
      this.fields = Lists.newArrayList();
    }
    field.setCompany(this);
    return this.fields.add(field);
  }
  
  /**
   * Removes the field.
   *
   * @param field the field
   * @return true, if list contained the specified element
   */
  public boolean removeField(CompanyFieldEntity field) {
    Preconditions.checkNotNull(field);
    field.setCompany(null);
    if (this.fields != null) {
      return this.fields.remove(field);
    }
    return false;
  }
  
  /**
   * Gets the Company record identifier.
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
  public long getLastModificationId() {
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

  public final static class Builder {
	  private final CompanyEntity entity = new CompanyEntity();
	  
	  public Builder(ActivityStatus status, String name) {
		  entity.setStatus(status);
		  entity.setName(name);
	  }
	  
	  public CompanyEntity build() {
	    return entity;
	  }

	  public Builder status(ActivityStatus status) {
	    entity.setStatus(status);
	    return this;
	  }

	  public Builder name(String name) {
	    entity.setName(name);
	    return this;
	  }

	  public Builder address(AddressEntity address) {
	    entity.setAddress(address);
	    return this;
	  }

	  public Builder contact(ContactEntity contact) {
	    entity.setContact(contact);
	    return this;
	  }

	  public Builder addWorkingHour(CompanyWorkingHourEntity... workingHours) {
		  for(CompanyWorkingHourEntity workingHour : workingHours) {
			  entity.addWorkingHour(workingHour);
		  }
	    return this;
	  }

	  public Builder addVacation(CompanyVacationEntity... vacations) {
		  for(CompanyVacationEntity vacation : vacations) {
			  entity.addVacation(vacation);
		  }
	    return this;
	  }

	  public Builder addField(CompanyFieldEntity... fields) {
		  for(CompanyFieldEntity field : fields) {
			  entity.addField(field);
		  }
	    return this;
	  }
	  
  }
}
