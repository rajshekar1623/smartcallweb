package com.aakhya.smartcall.application.admin.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aakhya.smartcall.application.admin.entity.GenericKey;
import com.aakhya.smartcall.application.admin.entity.RecordStatusType;
import com.aakhya.smartcall.application.admin.repository.GenericKeyRepository;

@Service
public class GenericKeyService {

	@Autowired
	private GenericKeyRepository genericKeyRepository;

	public List<GenericKey> findAllGenericKeys(String description,String genericKey) {
		if(null != description && !description.isEmpty()
				&& null != genericKey && !genericKey.isEmpty()) {
			return genericKeyRepository.findByDescriptionAndKey(description, genericKey);
		}else if(null != description && !description.isEmpty()) {
			return genericKeyRepository.findByDescription(description);
		}else if(null != genericKey && !genericKey.isEmpty()) {
			return genericKeyRepository.findByKey(genericKey);
		}else {
			return genericKeyRepository.findAll();
		} 
	}

	public void saveGenericKey(GenericKey genericKey) {
		if (null != genericKey && null != genericKey.getGenericKey() 
				&& !genericKey.getGenericKey().isEmpty()) {
			genericKey.setCompanyId(1L);
			genericKey.setStatus(RecordStatusType.ACTIVE.getValue());
			genericKeyRepository.save(genericKey);
		}
	}

	public void deleteGenericKey(GenericKey genericKey) {
		if (null != genericKey && null != genericKey.getGenericKey() 
				&& !genericKey.getGenericKey().isEmpty()) {
			genericKey.setStatus(RecordStatusType.DELETED.getValue());
			genericKeyRepository.save(genericKey);
		}
	}
	
	public void deleteGenericKeys(Set<GenericKey> genericKeys) {
		if(null != genericKeys && !genericKeys.isEmpty()) {
			for(GenericKey genericKey:genericKeys) {
				genericKey.setStatus(RecordStatusType.DELETED.getValue());
				genericKeyRepository.save(genericKey);
			}
		}
	}
}
