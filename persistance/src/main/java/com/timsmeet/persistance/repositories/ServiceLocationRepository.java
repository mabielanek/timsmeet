package com.timsmeet.persistance.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.timsmeet.persistance.model.ServiceLocationEntity;

public interface ServiceLocationRepository extends CrudRepository<ServiceLocationEntity, Long> {
  
  @Query("select sl from ServiceLocationEntity sl where sl.company.id = ?1")
  List<ServiceLocationEntity> findServiceLocationsByCompanyId(Long companyId);

}
