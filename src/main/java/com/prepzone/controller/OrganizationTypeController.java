package com.prepzone.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.prepzone.entity.OrganizationType;
import com.prepzone.repository.OrganizationTypeRepository;

@RestController
@RequestMapping("/resources/organizationtypes")
public class OrganizationTypeController {
	
	
	@Autowired
	OrganizationTypeRepository organizationTypeRespository;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<OrganizationType> retrieveOrganizationTypes(){
		
		List<OrganizationType> organizationTypes = new ArrayList<OrganizationType>();
		
		for(OrganizationType organizationType : organizationTypeRespository.findAll()){
			organizationTypes.add(organizationType);
		}
		
		return organizationTypes;
	}
	
}
