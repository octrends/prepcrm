package com.prepzone;

import org.h2.server.web.WebServlet;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.prepzone.entity.Address;
import com.prepzone.entity.Contact;
import com.prepzone.entity.Organization;
import com.prepzone.entity.OrganizationType;
import com.prepzone.repository.ContactRepository;
import com.prepzone.repository.OrganizationRepository;
import com.prepzone.repository.OrganizationTypeRepository;

/**
 * @author PrepZone Application boot
 */
@SpringBootApplication
public class SimpleCrmApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleCrmApplication.class, args);
	}

	/**
	 * Binds the database console to allow database analysis
	 * 
	 * @return
	 */
	@Bean
	public ServletRegistrationBean h2servletRegistration() {
		ServletRegistrationBean registration = new ServletRegistrationBean(new WebServlet());
		registration.addUrlMappings("/console/*");
		return registration;
	}

	/**
	 * Load the initial elements required to run the application
	 * 
	 * @param organizationTypeRepository
	 * @return
	 */
	@Bean

	CommandLineRunner init(final OrganizationTypeRepository organizationTypeRepository,
			final OrganizationRepository organizationRepository, final ContactRepository contactRepository) {

		return new CommandLineRunner() {

			@Override
			public void run(String... arg0) throws Exception {

				OrganizationType company = new OrganizationType();
				company.setName("Company");

				OrganizationType group = new OrganizationType();
				group.setName("Group");

				OrganizationType business = new OrganizationType();
				business.setName("Business");

				OrganizationType charity = new OrganizationType();
				charity.setName("Charity");

				organizationTypeRepository.save(company);
				organizationTypeRepository.save(group);
				organizationTypeRepository.save(business);
				organizationTypeRepository.save(charity);

				Contact contact1 = new Contact();
				contact1.setFirstName("Prep");
				contact1.setLastName("Zone");
				contact1.setPhone("30932039209");
				contact1.setEmail("root@prepzone.com");

				Address address = new Address();
				address.setCity("Toronto");
				address.setCountry("USA");
				address.setNumber("10A");
				address.setPostalCode("12345");
				address.setProvince("Orange");
				address.setStreet("Melita Ave");
				contact1.setAddress(address);

				Organization organization = new Organization();
				organization.setName("My company");
				organization.setPhone("22902293");
				organization.setEmail("webmaster@mycompany.com");
				organization.setAddress(address);

				organization.setOrganizationType(
						organizationTypeRepository.findByNameContainingIgnoreCase("company").get(0));
				organizationRepository.save(organization);
				contact1.setOrganization(organization);

				contactRepository.save(contact1);
			}

		};
	}
}
