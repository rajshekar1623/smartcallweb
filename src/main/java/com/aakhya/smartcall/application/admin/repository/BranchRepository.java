package com.aakhya.smartcall.application.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.aakhya.smartcall.application.admin.entity.Branch;
import com.aakhya.smartcall.application.admin.entity.BranchPk;

@Repository
public interface BranchRepository extends JpaRepository<Branch, BranchPk> {

	@Query("select c from Branch c " +
		      "where status <> 'X'") 
	List<Branch> findAll();
	@Query("select c from Branch c " +
      "where lower(c.branchName) like lower(concat('%', :searchTerm, '%')) and status <> 'X'") 
    List<Branch> search(@Param("searchTerm") String searchTerm);
	@Query("select c from Branch c " +
		      "where parentBranch is null and status <> 'X' ") 
	List<Branch> findTopLevelBranches();
	@Query("select c from Branch c " +
		      "where parentBranch is null and lower(c.branchName) like lower(concat('%', :searchTerm, '%')) and status <> 'X'") 
	List<Branch> findTopLevelBranches(@Param("searchTerm") String searchTerm);
	@Query("select c from Branch c " +
		      "where parentBranch = :parentBranch  and status <> 'X'") 
	List<Branch> findBranchesByHirearchy(@Param("parentBranch") String parentBranch);
	@Query("select c from Branch c " +
		      "where parentBranch = :parentBranch and lower(c.branchName) like lower(concat('%', :searchTerm, '%')) and status <> 'X'") 
	List<Branch> findBranchesByHirearchy(@Param("parentBranch") String parentBranch,@Param("searchTerm") String searchTerm);
	@Query("select c from Branch c " +
		      "where branchType = :branchType  and status <> 'X'") 
	List<Branch> findBranchesByType(@Param("branchType") Long branchType);
}
