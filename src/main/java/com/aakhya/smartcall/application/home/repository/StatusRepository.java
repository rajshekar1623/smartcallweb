package com.aakhya.smartcall.application.home.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

import com.aakhya.smartcall.application.home.entity.Status;

public interface StatusRepository extends JpaRepository<Status, UUID> {

}
