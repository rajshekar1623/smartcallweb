package com.aakhya.smartcall.application.transaction.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aakhya.smartcall.application.transaction.data.entity.TemporaryTransaction;

@Repository
public interface TemporaryTransactionRepository extends JpaRepository<TemporaryTransaction, String> {

}
