package com.timsmeet.persistance.repositories;

import org.springframework.data.repository.CrudRepository;

import com.timsmeet.persistance.model.CompanyEntity;

public interface CompanyRepository extends CrudRepository<CompanyEntity, Long> {

}
