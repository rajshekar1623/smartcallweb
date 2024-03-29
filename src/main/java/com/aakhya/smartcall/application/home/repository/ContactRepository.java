package com.aakhya.smartcall.application.home.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.aakhya.smartcall.application.home.entity.Contact;

public interface ContactRepository extends JpaRepository<Contact, UUID> {
	
	@Query("select c from Contact c " +
		      "where lower(c.firstName) like lower(concat('%', :searchTerm, '%')) " +
		      "or lower(c.lastName) like lower(concat('%', :searchTerm, '%'))") 
		    List<Contact> search(@Param("searchTerm") String searchTerm);

}
