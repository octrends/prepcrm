package com.prepzone.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.prepzone.entity.Contact;

public interface ContactRepository extends CrudRepository<Contact, Integer>{

	public List<Contact> findByFirstNameContainingIgnoreCase(String firstName);
	
	public List<Contact> findByOrganizationId(Integer id);
}
