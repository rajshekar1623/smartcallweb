package com.aakhya.smartcall.application.home.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

import com.aakhya.smartcall.application.home.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, UUID> {

}
