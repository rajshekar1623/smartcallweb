package com.aakhya.smartcall.application.security.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.aakhya.smartcall.application.security.entity.User;

public interface UserRepository extends JpaRepository<User, String> {

	
	@Query("select c from User c where c.status <> 'X' ") 
	List<User> findAllUsers();
	@Query("select c from User c " +
		      " where lower(c.userName) like lower(concat('%', :searchTerm, '%')) and c.status <> 'X' ") 
	List<User> search(@Param("searchTerm") String searchTerm);
	
	@Query("select c from User c " +
		      " where c.branchCode = :branchCode and c.status <> 'X'") 
	List<User> searchByBranch(@Param("branchCode") String branchCode);
	
	@Query("select c from User c " +
		      " where lower(c.userName) like lower(concat('%', :searchTerm, '%')) "
		      + " and c.branchCode = :branchCode and c.status <> 'X'") 
	List<User> searchByBranchAndName(@Param("searchTerm") String searchTerm,@Param("branchCode") String branchCode);
	
	@Query("select c from User c " +
		      " where c.branchCode = :branchCode and c.status <> 'X'") 
	List<User> findUsersByBranch(@Param("branchCode") String branchCode);
	
	@Query("select c from User c left outer join c.userRoles d " +
		      " where d.roleId = :roleId and c.status <> 'X'") 
	List<User> findUsersByRole(@Param("roleId") Long roleId);
	
//	@Override
//	@Query("select c from User c left outer join c.userRoles d ") 
//	List<User> findAll();
}
