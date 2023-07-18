package com.aakhya.smartcall.application.admin.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aakhya.smartcall.application.admin.entity.EntityNameType;
import com.aakhya.smartcall.application.admin.entity.GenericClassifier;
import com.aakhya.smartcall.application.admin.entity.RecordStatusType;
import com.aakhya.smartcall.application.admin.repository.GenericClassifierRepository;

@Service
public class GenericClassifierService {

	@Autowired
	private GenericClassifierRepository genericClassifierRepository;
	@Autowired
	private SequenceService sequenceService;

	public List<GenericClassifier> findAllClassifiers(String filterDescription,String genericKey) {
		if(null != filterDescription && !filterDescription.isEmpty()
				&& null != genericKey && !genericKey.isEmpty()) {
			return genericClassifierRepository.findByDescriptionAndKey(filterDescription, genericKey);
		}else if(null != filterDescription && !filterDescription.isEmpty()) {
			return genericClassifierRepository.search(filterDescription);
		}else if(null != genericKey && !genericKey.isEmpty()) {
			return genericClassifierRepository.findByGenericKey(genericKey);
		}else {
			return genericClassifierRepository.findAll();
		}
	}

	public List<GenericClassifier> findByGenericKey(String genericKey) {
		List<GenericClassifier> classifiers = new ArrayList<GenericClassifier>();
		if (null != genericKey && !genericKey.isEmpty())
			classifiers = genericClassifierRepository.findByGenericKey(genericKey);
		return classifiers;
	}

	public void saveGenericClassifier(GenericClassifier genericClassifier) {
		if (null != genericClassifier && null == genericClassifier.getGenericId()) {
			genericClassifier.setCompanyId(1L);
			genericClassifier.setStatus(RecordStatusType.ACTIVE.getValue());
			Long genericId = sequenceService.getNewSequence(EntityNameType.ADMIN_GENERIC_CLASSIFIER,
					genericClassifier.getCompanyId());
			genericClassifier.setGenericId(genericId);
		}
		genericClassifierRepository.save(genericClassifier);
	}

	public void deleteGenericClassifier(GenericClassifier genericClassifier) {
		if (null != genericClassifier) {
			genericClassifier.setStatus(RecordStatusType.DELETED.getValue());
			genericClassifierRepository.save(genericClassifier);
		}
	}
	
	public void deleteGenericClassifiers(Set<GenericClassifier> classifiers) {
		if(null != classifiers && !classifiers.isEmpty()) {
			for(GenericClassifier genericClassifier:classifiers) {
				genericClassifier.setStatus(RecordStatusType.DELETED.getValue());
				genericClassifierRepository.save(genericClassifier);
			}
		}
	}
}
