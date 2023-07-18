package com.aakhya.smartcall.application.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.aakhya.smartcall.application.admin.entity.GenericKey;
import com.aakhya.smartcall.application.admin.entity.GenericKeyPk;


public interface GenericKeyRepository extends JpaRepository<GenericKey, GenericKeyPk> {
	
	
	@Query("select c from GenericKey c " + "where c.description = :description and c.genericKey = :genericKey and status <> 'X'")
	List<GenericKey> findByDescriptionAndKey(@Param("description") String description,
			@Param("genericKey") String genericKey);
	
	@Query("select c from GenericKey c " + "where c.description = :description and status <> 'X'")
	List<GenericKey> findByDescription(@Param("description") String description);
	
	@Query("select c from GenericKey c " + "where c.genericKey = :genericKey and status <> 'X'")
	List<GenericKey> findByKey(@Param("genericKey") String genericKey);
	
	@Query("select c from GenericKey c " + "where status <> 'X'")
	List<GenericKey> findAll();

	@Query("select c from GenericKey c "
			+ "where lower(c.description) like lower(concat('%', :searchTerm, '%')) and status <> 'X'")
	List<GenericKey> search(@Param("searchTerm") String searchTerm);
}
