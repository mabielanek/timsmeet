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
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;



@Entity
@Table(name = "tm_serv_loc_serv_type",
  indexes={@Index(columnList="service_type_id", name="idx_slst_serv_type_fk"),
           @Index(columnList="service_location_id", name="idx_slst_serv_location_fk")}
)
public class ServiceLocationServiceTypeEntity {

  @Id
  @GeneratedValue(generator = "servLocServTypeGenerator")
  @GenericGenerator(name = "servLocServTypeGenerator", strategy="org.hibernate.id.enhanced.SequenceStyleGenerator", 
  parameters = { 
    @Parameter(name = "sequence_name", value="seq_tm_serv_loc_serv_type_id")
  })
  private long id;
  
  @Version
  @Column(name = "last_modification_id")
  private long lastModificationId;

  @ManyToOne
  @JoinColumn(name = "service_type_id", nullable = false, foreignKey=@ForeignKey(name="slst_serv_type_fk"))
  private ServiceTypeEntity serviceType;

  @ManyToOne
  @JoinColumn(name = "service_location_id", nullable = false, foreignKey=@ForeignKey(name="slst_serv_location_fk"))
  private ServiceLocationEntity serviceLocation;
  
  @OneToMany(cascade = {CascadeType.ALL})
  @JoinColumn(name = "serv_loc_serv_type_id")
  private List<ServiceLocationServiceTypeWorkingHourEntity> workingHours = new ArrayList<ServiceLocationServiceTypeWorkingHourEntity>();


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
   * Gets the service location.
   *
   * @return the service location
   */
  public ServiceLocationEntity getServiceLocation() {
    return serviceLocation;
  }

  /**
   * Sets the service location.
   *
   * @param serviceLocation the new service location
   */
  void setServiceLocation(ServiceLocationEntity serviceLocation) {
    this.serviceLocation = serviceLocation;
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

  
  public boolean addWorkingHour(ServiceLocationServiceTypeWorkingHourEntity workingHour) {
    if (this.workingHours == null) {
      this.workingHours = Lists.newArrayList();
    }
    workingHour.setServiceLocationServiceType(this);
    return this.workingHours.add(workingHour);
  }
  
  public boolean removeWorkingHour(ServiceLocationServiceTypeWorkingHourEntity workingHour) {
    Preconditions.checkNotNull(workingHour);
    workingHour.setServiceLocationServiceType(null);
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
  public List<ServiceLocationServiceTypeWorkingHourEntity> getWorkingHours() {
    return workingHours;
  }

	public final static class Builder {
		private ServiceLocationServiceTypeEntity entity = new ServiceLocationServiceTypeEntity();

		public ServiceLocationServiceTypeEntity build() {
			return entity;
		}

		public Builder addWorkingHour(
				ServiceLocationServiceTypeWorkingHourEntity... workingHours) {
			for (ServiceLocationServiceTypeWorkingHourEntity workingHour : workingHours) {
				entity.addWorkingHour(workingHour);
			}
			return this;
		}
	}
}
