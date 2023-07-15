package com.aakhya.smartcall.application.integration.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aakhya.smartcall.application.integration.entity.IntegrationMaster;
import com.aakhya.smartcall.application.integration.entity.IntegrationMasterPk;

public interface IntegrationMasterRepository extends JpaRepository<IntegrationMaster, IntegrationMasterPk> {

}
