package com.timsmeet.persistance.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
@DiscriminatorValue("S")
public class ServiceLocationServiceTypeWorkingHourEntity extends WorkingHourEntity {
  
  @ManyToOne
  @JoinColumn(name = "serv_loc_serv_type_id", foreignKey=@ForeignKey(name="wrk_hour_slst_fk"))
  private ServiceLocationServiceTypeEntity serviceLocationServiceType;

  /**
   * Gets the service location service type.
   *
   * @return the service location service type
   */
  public ServiceLocationServiceTypeEntity getServiceLocationServiceType() {
    return serviceLocationServiceType;
  }

  /**
   * Sets the service location service type.
   *
   * @param serviceLocationServiceType the new service location service type
   */
  void setServiceLocationServiceType(ServiceLocationServiceTypeEntity serviceLocationServiceType) {
    this.serviceLocationServiceType = serviceLocationServiceType;
  }


	public final static class Builder extends WorkingHourEntity.Builder<ServiceLocationServiceTypeWorkingHourEntity> {
		public Builder() {
			super(new ServiceLocationServiceTypeWorkingHourEntity());
		}

	}
}
