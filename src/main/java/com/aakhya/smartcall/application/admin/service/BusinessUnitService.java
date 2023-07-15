package com.aakhya.smartcall.application.admin.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aakhya.smartcall.application.admin.entity.BusinessProcessUnit;
import com.aakhya.smartcall.application.admin.entity.BusinessUnit;
import com.aakhya.smartcall.application.admin.entity.BusinessUnitPK;
import com.aakhya.smartcall.application.admin.entity.EntityNameType;
import com.aakhya.smartcall.application.admin.entity.RecordStatusType;
import com.aakhya.smartcall.application.admin.repository.BusinessUnitRepository;

@Service
public class BusinessUnitService {

	
	@Autowired
	private BusinessUnitRepository businessUnitRepository;
	@Autowired
	private SequenceService sequenceService;
	
public List<BusinessUnit> findAllBusinessUnits(){
		List<BusinessUnit> businessUnits = businessUnitRepository.findAll();
		return businessUnits;
		}

public List<BusinessUnit> findAllBusinessUnits(String businessUnitNameFilter){
	List<BusinessUnit> businessUnits = new ArrayList<BusinessUnit>();
	if(null == businessUnitNameFilter || businessUnitNameFilter.isEmpty()) {
		businessUnits = businessUnitRepository.findAll();
	}else {
		businessUnits = businessUnitRepository.search(businessUnitNameFilter);
	}
	return businessUnits;
}

public BusinessUnit findByPrimaryKey(BusinessUnitPK businessUnitPK) {
	Optional<BusinessUnit> op = businessUnitRepository.findById(businessUnitPK);
	if(op.isPresent()) {
		BusinessUnit bu = op.get();
		return bu;
	}
	else {
		return null;
	}
}


public void saveBusinessUnit(BusinessUnit businessUnit) {
	if(null!= businessUnit) {
		if(null == businessUnit.getBusinessUnitId()) {
			Long businessUnitId = sequenceService.getNewSequence(EntityNameType.BUSINESS_UNIT, businessUnit.getCompanyId());
			businessUnit.setBusinessUnitId(businessUnitId);
			if(null != businessUnit.getBusinessProcessUnits() && !businessUnit.getBusinessProcessUnits().isEmpty()) {
				for(BusinessProcessUnit businessProcessUnit:businessUnit.getBusinessProcessUnits()) {
					businessProcessUnit.setBusinessUnit(businessUnit);
				}
			}
		}
		businessUnitRepository.save(businessUnit);
		}
}

public void deleteBusinessUnit(BusinessUnit businessUnit) {
	if(null!=businessUnit) {
		businessUnit.setStatus(RecordStatusType.DELETED.getValue());
		businessUnitRepository.delete(businessUnit);
	}
}

	
}
