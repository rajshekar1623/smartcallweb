package com.aakhya.smartcall.application.security.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.aakhya.smartcall.application.security.entity.UserRole;
import com.aakhya.smartcall.application.security.entity.UserRolePk;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, UserRolePk> {

	@Query("select c from UserRole c where c.userId = :userId")
	List<UserRole> findExistingRolesForUser(@Param("userId") String userId);
}
