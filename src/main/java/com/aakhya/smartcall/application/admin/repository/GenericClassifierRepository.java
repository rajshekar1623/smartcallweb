package com.aakhya.smartcall.application.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.aakhya.smartcall.application.admin.entity.GenericClassifier;
import com.aakhya.smartcall.application.admin.entity.GenericClassifierPk;


public interface GenericClassifierRepository extends JpaRepository<GenericClassifier, GenericClassifierPk> {
	@Query("select c from GenericClassifier c "
			+ "where status <> 'X'")
	List<GenericClassifier> findAll();
	
	@Query("select c from GenericClassifier c "
			+ "where lower(c.description) like lower(concat('%', :description, '%')) "
			+ "and c.genericKey = :genericKey and status <> 'X'")
	List<GenericClassifier> findByDescriptioAndKey(@Param("description") String description,
			@Param("genericKey") String genericKey);
	
	@Query("select c from GenericClassifier c "
			+ "where c.genericKey = :genericKey and status <> 'X'")
	List<GenericClassifier> findByKey(@Param("genericKey") String genericKey);
	
	@Query("select c from GenericClassifier c "
			+ "where lower(c.description) like lower(concat('%', :searchTerm, '%')) and status <> 'X'")
	List<GenericClassifier> search(@Param("searchTerm") String searchTerm);

	@Query("select c from GenericClassifier c " + "where genericKey = :genericKey and status <> 'X'")
	List<GenericClassifier> findByGenericKey(@Param("genericKey") String genericKey);
}
