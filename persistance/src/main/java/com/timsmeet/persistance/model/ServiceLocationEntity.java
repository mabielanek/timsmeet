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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.timsmeet.persistance.enums.ActivityStatus;

@Entity
@Table(name = "tm_service_location",
  indexes={@Index(columnList="company_id", name="idx_serv_loc_comopany_fk"),
           @Index(columnList="address_id", name="idx_serv_loc_address_fk"),
           @Index(columnList="contact_id", name="idx_serv_loc_contact_fk")}
)
public class ServiceLocationEntity {
  
  @Id
  @GeneratedValue(generator = "serviceLocationGenerator")
  @GenericGenerator(name = "serviceLocationGenerator", strategy="org.hibernate.id.enhanced.SequenceStyleGenerator", 
  parameters = { 
    @Parameter(name = "sequence_name", value="seq_tm_service_location_id")
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
  
  @Column(name = "description", length = 1024)
  private String description;

  @ManyToOne
  @JoinColumn(name = "company_id", foreignKey=@ForeignKey(name="serv_loc_company_fk"))
  private CompanyEntity company;

  @OneToOne(cascade = {CascadeType.ALL})
  @JoinColumn(name = "address_id", foreignKey=@ForeignKey(name="serv_loc_address_fk"))
  private AddressEntity address;
  
  @OneToOne(cascade = {CascadeType.ALL})
  @JoinColumn(name = "contact_id", foreignKey=@ForeignKey(name="serv_loc_contact_fk"))
  private ContactEntity contact;
  
  @OneToMany(cascade = {CascadeType.ALL})
  @JoinColumn(name = "service_location_id")
  private List<ServiceLocationWorkingHourEntity> workingHours = new ArrayList<ServiceLocationWorkingHourEntity>();

  @OneToMany(cascade = {CascadeType.ALL})
  @JoinColumn(name = "service_location_id")
  private List<ServiceLocationServiceTypeEntity> serviceLocationServiceTypes = new ArrayList<ServiceLocationServiceTypeEntity>();
  
  @OneToMany(cascade = {CascadeType.ALL})
  @JoinColumn(name = "service_location_id")
  private List<ServiceLocationVacationEntity> vacations = new ArrayList<ServiceLocationVacationEntity>();
  
  @OneToMany(cascade = {CascadeType.ALL})
  @JoinColumn(name = "service_location_id")
  private List<ServiceLocationFieldEntity> fields = new ArrayList<ServiceLocationFieldEntity>();

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
   * Gets the name.
   *
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the name.
   *
   * @param name the new name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Gets the description.
   *
   * @return the description
   */
  public String getDescription() {
    return description;
  }

  /**
   * Sets the description.
   *
   * @param description the new description
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * Gets the owning company.
   *
   * @return the company
   */
  public CompanyEntity getCompany() {
    return company;
  }

  /**
   * Sets the company, that owns this service location.
   *
   * @param company the new company
   */
  public void setCompany(CompanyEntity company) {
    this.company = company;
  }

  /**
   * Gets the address.
   *
   * @return the address
   */
  public AddressEntity getAddress() {
    return address;
  }

  /**
   * Sets the address.
   *
   * @param address the new address
   */
  public void setAddress(AddressEntity address) {
    this.address = address;
  }

  /**
   * Gets the contact.
   *
   * @return the contact
   */
  public ContactEntity getContact() {
    return contact;
  }

  /**
   * Sets the contact.
   *
   * @param contact the new contact
   */
  public void setContact(ContactEntity contact) {
    this.contact = contact;
  }

  /**
   * Gets the ServiceLocation record identifier.
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


  /**
   * Adds the working hour.
   *
   * @param workingHour the working hour
   * @return true, if collection changed as a result of the call
   */
  public boolean addWorkingHour(ServiceLocationWorkingHourEntity workingHour) {
    if (this.workingHours == null) {
      this.workingHours = Lists.newArrayList();
    }
    workingHour.setServiceLocation(this);
    return this.workingHours.add(workingHour);
  }
  
  /**
   * Removes the working hour.
   *
   * @param workingHour the working hour
   * @return true, if list contained the specified element
   */
  public boolean removeWorkingHour(ServiceLocationWorkingHourEntity workingHour) {
    Preconditions.checkNotNull(workingHour);
    workingHour.setServiceLocation(null);
    if (this.workingHours != null) {
      return this.workingHours.remove(workingHour);
    }
    return false;
  }

  /**
   * Gets the working hours.
   *
   * @return the working hours
   */
  public List<ServiceLocationWorkingHourEntity> getWorkingHours() {
    return workingHours;
  }
  
  /**
   * Adds the service location service type.
   *
   * @param serviceLocationServiceType the service location service type
   * @return true, if collection changed as a result of the call
   */
  public boolean addServiceLocationServiceType(ServiceLocationServiceTypeEntity serviceLocationServiceType) {
    if (this.serviceLocationServiceTypes == null) {
      this.serviceLocationServiceTypes = Lists.newArrayList();
    }
    serviceLocationServiceType.setServiceLocation(this);
    return this.serviceLocationServiceTypes.add(serviceLocationServiceType);
  }
  
  /**
   * Removes the service location service type.
   *
   * @param serviceLocationServiceType the service location service type
   * @return true, if list contained the specified element
   */
  public boolean removeServiceLocationServiceType(ServiceLocationServiceTypeEntity serviceLocationServiceType) {
    Preconditions.checkNotNull(serviceLocationServiceType);
    serviceLocationServiceType.setServiceLocation(null);
    if (this.serviceLocationServiceTypes != null) {
      return this.serviceLocationServiceTypes.remove(serviceLocationServiceType);
    }
    return false;
  }
  
  /**
   * Gets the service location service types.
   *
   * @return the service location service types
   */
  public List<ServiceLocationServiceTypeEntity> getServiceLocationServiceTypes() {
    return serviceLocationServiceTypes;
  }
  
  /**
   * Adds the vacation.
   *
   * @param vacation the company vacation
   * @return true, if collection changed as a result of the call
   */
  public boolean addVacation(ServiceLocationVacationEntity vacation) {
    if (this.vacations == null) {
      this.vacations = Lists.newArrayList();
    }
    vacation.setServiceLocation(this);
    return this.vacations.add(vacation);
  }
  
  /**
   * Removes the vacation.
   *
   * @param vacation the vacation
   * @return true, if list contained the specified element
   */
  public boolean removeVacation(ServiceLocationVacationEntity vacation) {
    Preconditions.checkNotNull(vacation);
    vacation.setServiceLocation(null);
    if (this.vacations != null) {
      return this.vacations.remove(vacation);
    }
    return false;
  }
  
  /**
   * Gets the vacations.
   *
   * @return the vacations
   */
  public List<ServiceLocationVacationEntity> getVacations() {
    return vacations;
  }
  
  /**
   * Adds the field.
   *
   * @param field the field
   * @return true, if collection changed as a result of the call
   */
  public boolean addField(ServiceLocationFieldEntity field) {
    if (this.fields == null) {
      this.fields = Lists.newArrayList();
    }
    field.setServiceLocation(this);
    return this.fields.add(field);
  }
  
  /**
   * Removes the field.
   *
   * @param field the field
   * @return true, if list contained the specified element
   */
  public boolean removeField(ServiceLocationFieldEntity field) {
    Preconditions.checkNotNull(field);
    field.setServiceLocation(null);
    if (this.fields != null) {
      return this.fields.remove(field);
    }
    return false;
  }

  /**
   * Gets the fields.
   *
   * @return the fields
   */
  public List<ServiceLocationFieldEntity> getFields() {
    return fields;
  }

  
  public final static class Builder {
	  private ServiceLocationEntity entity = new ServiceLocationEntity();
	  
	  public ServiceLocationEntity build() {
		  return entity;
	  }
	  
	  public Builder(ActivityStatus status, String name) {
		  entity.setStatus(status);
		  entity.setName(name);
	  }

	public Builder status(ActivityStatus status) {
		entity.setStatus(status);
		return this;
	}

	public Builder name(String name) {
		entity.setName(name);
		return this;
	}

	public Builder description(String description) {
		entity.setDescription(description);
		return this;
	}

	public Builder company(CompanyEntity company) {
		entity.setCompany(company);
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

	public Builder addWorkingHour(ServiceLocationWorkingHourEntity... workingHours) {
		for(ServiceLocationWorkingHourEntity workingHour : workingHours) {
			entity.addWorkingHour(workingHour);
		}
		return this;
	}

	public Builder addServiceLocationServiceType(ServiceLocationServiceTypeEntity... serviceLocationServiceTypes) {
		for(ServiceLocationServiceTypeEntity serviceLocationServiceType : serviceLocationServiceTypes) {
			entity.addServiceLocationServiceType(serviceLocationServiceType);
		}
		return this;
	}

	public Builder addVacation(ServiceLocationVacationEntity... vacations) {
		for(ServiceLocationVacationEntity vacation : vacations) {
			entity.addVacation(vacation);
		}
		return this;
	}

	public Builder addField(ServiceLocationFieldEntity... fields) {
		for(ServiceLocationFieldEntity field : fields) {
			entity.addField(field);
		}
		return this;
	}
	  
  }
}
