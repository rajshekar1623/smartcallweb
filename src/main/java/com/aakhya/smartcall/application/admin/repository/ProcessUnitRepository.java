package com.aakhya.smartcall.application.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.aakhya.smartcall.application.admin.entity.ProcessUnit;
import com.aakhya.smartcall.application.admin.entity.ProcessUnitPK;

public interface ProcessUnitRepository extends JpaRepository<ProcessUnit, ProcessUnitPK>{

	@Query("select c from ProcessUnit c " +
		      "where lower(c.processingUnitName) like lower(concat('%', :searchTerm, '%')) ") 
		    List<ProcessUnit> search(@Param("searchTerm") String searchTerm);
	
	
	
	
}
