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
import com.timsmeet.persistance.constants.FieldValue;
import com.timsmeet.persistance.enums.ActivityStatus;
import com.timsmeet.persistance.enums.EmailPreferences;


@Entity
@Table(name = "tm_employee",
  indexes={@Index(columnList="contact_id", name="idx_employee_contact_fk")}
)
public class EmployeeEntity {

  @Id
  @GeneratedValue(generator = "employeeGenerator")
  @GenericGenerator(name = "employeeGenerator", strategy="org.hibernate.id.enhanced.SequenceStyleGenerator", 
  parameters = { 
    @Parameter(name = "sequence_name", value="seq_tm_employee_id"),
    @Parameter(name = "increment_size", value = "1"),
    @Parameter(name = "optimizer", value = "pooled")
  })
  private long id;
  
  @Version
  @Column(name = "last_modification_id")
  private long lastModificationId;
  
  @Column(name = "status", nullable = false, length = 1)
  @org.hibernate.annotations.Check(constraints = "status IN('A','I','D')")
  private String status = "A";
  
  @Column(name = "first_name", length = 100)
  private String firstName;
  
  @Column(name = "last_name", length = 100)
  private String lastName;
  
  @Column(name = "title", length = 100)
  private String title;
  
  @OneToOne(cascade = {CascadeType.ALL})
  @JoinColumn(name = "contact_id", foreignKey=@ForeignKey(name="employee_contact_fk"))
  private ContactEntity contact;
  
  @org.hibernate.annotations.Check(constraints = "email_preferences IN ('P','R')")
  @Column(name = "email_preferences", length = 1, nullable = false)
  private String emailPreferences = "P";
  
  @OneToMany(cascade = {CascadeType.ALL})
  @JoinColumn(name = "employee_id")
  private List<EmployeeWorkingHourEntity> workingHours = new ArrayList<EmployeeWorkingHourEntity>();

  @OneToMany(cascade = {CascadeType.ALL})
  @JoinColumn(name = "employee_id")
  private List<EmployeeServiceTypeEntity> serviceTypes = new ArrayList<EmployeeServiceTypeEntity>();
  
  @OneToMany(cascade = {CascadeType.ALL})
  @JoinColumn(name = "employee_id")
  private List<EmployeeVacationEntity> vacations = new ArrayList<EmployeeVacationEntity>();

  @OneToMany(cascade = {CascadeType.ALL})
  @JoinColumn(name = "employee_id")
  private List<EmployeeFieldEntity> fields = new ArrayList<EmployeeFieldEntity>();

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
   * Gets the first name.
   *
   * @return the first name
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * Sets the first name.
   *
   * @param firstName the new first name
   */
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  /**
   * Gets the last name.
   *
   * @return the last name
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * Sets the last name.
   *
   * @param lastName the new last name
   */
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  /**
   * Gets the title.
   *
   * @return the title
   */
  public String getTitle() {
    return title;
  }

  /**
   * Sets the title.
   *
   * @param title the new title
   */
  public void setTitle(String title) {
    this.title = title;
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
   * Gets the email preferences, one of: 'P' - plain text, 'R' - rich text(html).
   * Use {@link FieldValue.EmailPreferences}
   *
   * @return the email preferences
   */
  public EmailPreferences getEmailPreferences() {
    return EmailPreferences.forCode(emailPreferences);
  }

  /**
   * Sets the email preferences.
   *
   * @param emailPreferences the new email preferences
   */
  public void setEmailPreferences(EmailPreferences emailPreferences) {
    this.emailPreferences = emailPreferences.getCode();
  }

  /**
   * Adds the working hour.
   *
   * @param workingHour the working hour
   * @return true, if collection changed as a result of the call
   */
  public boolean addWorkingHour(EmployeeWorkingHourEntity workingHour) {
    if (this.workingHours == null) {
      this.workingHours = Lists.newArrayList();
    }
    workingHour.setEmployee(this);
    return this.workingHours.add(workingHour);
  }
  
  /**
   * Removes the working hour.
   *
   * @param workingHour the working hour
   * @return true, if list contained the specified element
   */
  public boolean removeWorkingHour(EmployeeWorkingHourEntity workingHour) {
    Preconditions.checkNotNull(workingHour);
    workingHour.setEmployee(null);
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
  public List<EmployeeWorkingHourEntity> getWorkingHours() {
    return workingHours;
  }
  
  /**
   * Adds the service type.
   *
   * @param serviceType the service type
   * @return true, collection changed as a result of the call
   */
  public boolean addServiceType(EmployeeServiceTypeEntity serviceType) {
    if (this.serviceTypes == null) {
      this.serviceTypes = Lists.newArrayList();
    }
    serviceType.setEmployee(this);
    return this.serviceTypes.add(serviceType);
  }
  
  /**
   * Removes the service type.
   *
   * @param serviceType the service type
   * @return true, if list contained the specified element
   */
  public boolean removeServiceType(EmployeeServiceTypeEntity serviceType) {
    Preconditions.checkNotNull(serviceType);
    serviceType.setEmployee(null);
    if (this.serviceTypes != null) {
      return this.serviceTypes.remove(serviceType);
    }
    return false;
  }
  
  /**
   * Gets the service types.
   *
   * @return the service types
   */
  public List<EmployeeServiceTypeEntity> getServiceTypes() {
    return serviceTypes;
  }
  
  /**
   * Adds the vacation.
   *
   * @param vacation the employee vacation
   * @return true, if collection changed as a result of the call
   */
  public boolean addVacation(EmployeeVacationEntity vacation) {
    if (this.vacations == null) {
      this.vacations = Lists.newArrayList();
    }
    vacation.setEmployee(this);
    return this.vacations.add(vacation);
  }
  
  /**
   * Removes the vacation.
   *
   * @param vacation the vacation
   * @return true, if list contained the specified element
   */
  public boolean removeVacation(EmployeeVacationEntity vacation) {
    Preconditions.checkNotNull(vacation);
    vacation.setEmployee(null);
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
  public List<EmployeeVacationEntity> getVacations() {
    return vacations;
  }
  
  /**
   * Adds the field.
   *
   * @param field the field
   * @return true, if collection changed as a result of the call
   */
  public boolean addField(EmployeeFieldEntity field) {
    if (this.fields == null) {
      this.fields = Lists.newArrayList();
    }
    field.setEmployee(this);
    return this.fields.add(field);
  }
  
  /**
   * Removes the field.
   *
   * @param field the field
   * @return true, if list contained the specified element
   */
  public boolean removeField(EmployeeFieldEntity field) {
    Preconditions.checkNotNull(field);
    field.setEmployee(null);
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
  public List<EmployeeFieldEntity> getFields() {
    return fields;
  }
  
  /**
   * Gets the Employee record identifier.
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

  public static final class Builder {
	  private EmployeeEntity entity = new EmployeeEntity();
	  
	  public EmployeeEntity build() {
		  return entity;
	  }

	public Builder status(ActivityStatus status) {
		entity.setStatus(status);
		return this;
	}

	public Builder firstName(String firstName) {
		entity.setFirstName(firstName);
		return this;
	}

	public Builder lastName(String lastName) {
		entity.setLastName(lastName);
		return this;
	}

	public Builder title(String title) {
		entity.setTitle(title);
		return this;
	}

	public Builder contact(ContactEntity contact) {
		entity.setContact(contact);
		return this;
	}

	public Builder emailPreferences(EmailPreferences emailPreferences) {
		entity.setEmailPreferences(emailPreferences);
		return this;
	}

	public Builder addWorkingHour(EmployeeWorkingHourEntity... workingHours) {
		for(EmployeeWorkingHourEntity workingHour : workingHours) {
			entity.addWorkingHour(workingHour);
		}
		return this;
	}

	public Builder addServiceType(EmployeeServiceTypeEntity... serviceTypes) {
		for(EmployeeServiceTypeEntity serviceType : serviceTypes) {
			entity.addServiceType(serviceType);
		}
		return this;
	}

	public Builder addVacation(EmployeeVacationEntity... vacations) {
		for(EmployeeVacationEntity vacation : vacations) {
			entity.addVacation(vacation);
		}
		return this;
	}

	public Builder addField(EmployeeFieldEntity... fields) {
		for(EmployeeFieldEntity field : fields) {
			entity.addField(field);
		}
		return this;
	}

  }
  
}
