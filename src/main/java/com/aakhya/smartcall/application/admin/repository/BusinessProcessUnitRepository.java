package com.aakhya.smartcall.application.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aakhya.smartcall.application.admin.entity.BusinessProcessUnit;
import com.aakhya.smartcall.application.admin.entity.BusinessProcessUnitPK;

@Repository
public interface BusinessProcessUnitRepository extends JpaRepository<BusinessProcessUnit, BusinessProcessUnitPK>{

}