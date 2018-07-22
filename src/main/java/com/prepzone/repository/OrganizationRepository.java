package com.prepzone.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.prepzone.entity.Organization;

public interface OrganizationRepository extends CrudRepository<Organization, Integer>{

	public List<Organization> findByNameContainingIgnoreCase(String name);
}
