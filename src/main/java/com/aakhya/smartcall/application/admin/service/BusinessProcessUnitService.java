package com.aakhya.smartcall.application.admin.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aakhya.smartcall.application.admin.entity.BusinessProcessUnit;
import com.aakhya.smartcall.application.admin.entity.BusinessProcessUnitPK;
import com.aakhya.smartcall.application.admin.entity.RecordStatusType;
import com.aakhya.smartcall.application.admin.repository.BusinessProcessUnitRepository;

@Service
public class BusinessProcessUnitService {
	
	@Autowired
	private BusinessProcessUnitRepository businessProcessUnitRepository;
	
	
	public List<BusinessProcessUnit> findAllBusinessProcessUnits(){
		List<BusinessProcessUnit> businessProcessUnits = businessProcessUnitRepository.findAll();
		return businessProcessUnits;
		
	} 
	
	
	public BusinessProcessUnit findByPrimaryKey(BusinessProcessUnitPK businessProcessUnitPK) {
		Optional<BusinessProcessUnit> op = businessProcessUnitRepository.findById(businessProcessUnitPK);
		if(op.isPresent()) {
			BusinessProcessUnit bpu = op.get();
			return bpu;
		}
		else {
			return null;
		}
	}
	
	
	public void saveBusinessProcessUnit(BusinessProcessUnit businessProcessUnit) {
		if(null!= businessProcessUnit) {
			businessProcessUnitRepository.save(businessProcessUnit);
		}
	}
	
	
	public void deleteBusinessProcessUnit(BusinessProcessUnit businessProcessUnit) {
		if(null!= businessProcessUnit) {
			businessProcessUnit.setStatus(RecordStatusType.DELETED.getValue());
			businessProcessUnitRepository.save(businessProcessUnit);
		}
	}
	
}
