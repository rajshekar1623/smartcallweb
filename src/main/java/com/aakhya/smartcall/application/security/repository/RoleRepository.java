package com.aakhya.smartcall.application.security.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.aakhya.smartcall.application.security.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	@Query("select c from Role c " +
		      "where lower(c.description) like lower(concat('%', :searchTerm, '%')) and c.status <> 'X' ") 
	List<Role> search(@Param("searchTerm") String searchTerm);
	
	@Query("select c from Role c where c.status <> 'X' ") 
	List<Role> findAllRoles();
}
