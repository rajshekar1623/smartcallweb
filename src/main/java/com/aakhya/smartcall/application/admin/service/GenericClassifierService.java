package com.aakhya.smartcall.application.admin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aakhya.smartcall.application.admin.entity.EntityNameType;
import com.aakhya.smartcall.application.admin.entity.GenericClassifier;
import com.aakhya.smartcall.application.admin.entity.GenericKey;
import com.aakhya.smartcall.application.admin.entity.RecordStatusType;
import com.aakhya.smartcall.application.admin.repository.GenericClassifierRepository;

@Service
public class GenericClassifierService {

	@Autowired
	private GenericClassifierRepository genericClassifierRepository;
	@Autowired
	private SequenceService sequenceService;

	public List<GenericClassifier> findAllClassifiers(String filterDescription) {
		if (null == filterDescription || filterDescription.isEmpty()) {
			List<GenericClassifier> genericClassifiers = genericClassifierRepository.findAll();
			if (null != genericClassifiers && !genericClassifiers.isEmpty()) {
				for (GenericClassifier genericClassifier : genericClassifiers) {
					if (null != genericClassifier.getGenericKey()
							&& null != genericClassifier.getGenericKeyDescription()) {
						genericClassifier.setKey(new GenericKey(genericClassifier.getGenericKey(),
								genericClassifier.getGenericKeyDescription()));
					}
					if (null != genericClassifier.getParentKey() && null != genericClassifier.getParentKeyStr()) {
						genericClassifier.setParentKeyObject(new GenericClassifier(genericClassifier.getParentKey(),
								genericClassifier.getParentKeyStr()));
					}
				}
			}
			return genericClassifiers;
		} else {
			return genericClassifierRepository.search(filterDescription);
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
}
