package com.aakhya.smartcall.application.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.aakhya.smartcall.application.admin.entity.BusinessUnit;
import com.aakhya.smartcall.application.admin.entity.BusinessUnitPK;

public interface BusinessUnitRepository extends JpaRepository<BusinessUnit, BusinessUnitPK> {
	@Query("select c from BusinessUnit c " +
		      "where lower(c.businessUnitName) like lower(concat('%', :searchTerm, '%')) ") 
		    List<BusinessUnit> search(@Param("searchTerm") String searchTerm);
}
