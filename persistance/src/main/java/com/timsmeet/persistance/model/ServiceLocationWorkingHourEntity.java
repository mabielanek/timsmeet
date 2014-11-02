package com.timsmeet.persistance.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
@DiscriminatorValue("L")
public class ServiceLocationWorkingHourEntity extends WorkingHourEntity {

  @ManyToOne
  @JoinColumn(name = "service_location_id", foreignKey=@ForeignKey(name="wrk_hour_serv_loc_fk"))
  private ServiceLocationEntity serviceLocation;

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
  
	public final static class Builder extends WorkingHourEntity.Builder<ServiceLocationWorkingHourEntity> {
		public Builder() {
			super(new ServiceLocationWorkingHourEntity());
		}

	}
  
}
