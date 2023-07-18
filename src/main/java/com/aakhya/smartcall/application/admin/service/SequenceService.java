package com.aakhya.smartcall.application.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aakhya.smartcall.application.admin.entity.EntityNameType;
import com.aakhya.smartcall.application.admin.repository.SequenceJDBCDao;

@Service
public class SequenceService {

	@Autowired
	private SequenceJDBCDao sequenceJDBCDao;
	
	public synchronized Long getNewSequence(EntityNameType entityNameType,Long companyId){

		return sequenceJDBCDao.getSequenceForEntity(entityNameType.getValue(), companyId);
	}
}
