package com.aakhya.smartcall.application.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aakhya.smartcall.application.admin.entity.GenericKey;
import com.aakhya.smartcall.application.admin.entity.RecordStatusType;
import com.aakhya.smartcall.application.admin.repository.GenericKeyRepository;

@Service
public class GenericKeyService {

	@Autowired
	private GenericKeyRepository genericKeyRepository;

	public List<GenericKey> findAllGenericKeys(String filterDescrption) {
		if (null == filterDescrption || filterDescrption.isEmpty()) {
			return genericKeyRepository.findAll();
		} else {
			return genericKeyRepository.search(filterDescrption);
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
}
