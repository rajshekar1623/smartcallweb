package com.aakhya.smartcall.application.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aakhya.smartcall.application.core.entity.GlobalParameter;
import com.aakhya.smartcall.application.core.entity.GlobalParameterPK;

public interface GlobalParameterRepository extends JpaRepository<GlobalParameter, GlobalParameterPK> {

}