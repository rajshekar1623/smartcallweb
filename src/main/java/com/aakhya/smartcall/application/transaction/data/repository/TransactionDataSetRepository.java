package com.aakhya.smartcall.application.transaction.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.aakhya.smartcall.application.transaction.data.entity.TransactionDataSet;

public interface TransactionDataSetRepository extends JpaRepository<TransactionDataSet, Long> {

	@Query("select c from TransactionDataSet c " + "where dataSetType =  :dataSetType")
	List<TransactionDataSet> findAllByDataSetType(@Param("dataSetType") Long dataSetType);

	@Query("select c from TransactionDataSet c where genericNumber2 =  :loanAccountNumber")
	TransactionDataSet findDataSetByLoanAccountNumber(Long loanAccountNumber);

	@Query("select count(c) from TransactionDataSet c ")
	Long queryForCount();

	@Query("select c from TransactionDataSet c " + "where dataSetType =  :dataSetType and branchCode = :branchCode")
	List<TransactionDataSet> findAllByDataSetTypeAndBranch(@Param("dataSetType") Long dataSetType,
			@Param("branchCode") String branchCode);
}
