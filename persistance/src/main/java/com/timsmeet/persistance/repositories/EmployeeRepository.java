package com.timsmeet.persistance.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.timsmeet.persistance.model.EmployeeEntity;

public interface EmployeeRepository extends CrudRepository<EmployeeEntity, Long> {
	
	List<EmployeeEntity> findByCompanyId(Long companyId);

}
