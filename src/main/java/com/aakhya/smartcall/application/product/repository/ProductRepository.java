package com.aakhya.smartcall.application.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.aakhya.smartcall.application.product.entity.Product;
import com.aakhya.smartcall.application.product.entity.ProductPk;

@Repository
public interface ProductRepository extends JpaRepository<Product, ProductPk> {

	@Query("select c from Product c where c.status = 'A' and lower(c.productName) like lower(concat('%', :searchTerm, '%')) order by c.productName")
	List<Product> findAllProducts(@Param("searchTerm") String searchTerm);
	
	@Query("select c from Product c where c.status <> 'X' and lower(c.productName) "
			+ "like lower(concat('%', :productName, '%')) and c.productCode = :productCode order by c.productName")
	List<Product> findByNameAndCode(@Param("productName") String productName,
			@Param("productCode") String productCode);
	
	@Query("select c from Product c where c.status <> 'X' and lower(c.productName) "
			+ "like lower(concat('%', :productName, '%')) order by c.productName")
	List<Product> findByName(@Param("productName") String productName);
	
	@Query("select c from Product c where c.status <> 'X' and c.productCode = :productCode order by c.productName")
	List<Product> findByCode(@Param("productCode") String productCode);
	
	@Query("select c from Product c where c.status = 'A' order by c.productName ")
	List<Product> findAll();
}
