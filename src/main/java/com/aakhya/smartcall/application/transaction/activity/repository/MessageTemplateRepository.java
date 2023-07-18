package com.aakhya.smartcall.application.transaction.activity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.aakhya.smartcall.application.transaction.activity.entity.MessageTemplate;

@Repository
public interface MessageTemplateRepository extends JpaRepository<MessageTemplate, Long> {

	@Query("select c from MessageTemplate c where c.status <> 'X'")
	List<MessageTemplate> findMessageTemplates();
	
	@Query("select c from MessageTemplate c where c.status <> 'X' and messageType = :messageType and approvalStatus = 'APPROVED'")
	List<MessageTemplate> findApprovedMessageTemplates(@Param("messageType")String messageType);
}
