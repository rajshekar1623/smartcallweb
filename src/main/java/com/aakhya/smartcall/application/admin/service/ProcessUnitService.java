package com.aakhya.smartcall.application.admin.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aakhya.smartcall.application.admin.entity.EntityNameType;
import com.aakhya.smartcall.application.admin.entity.ProcessUnit;
import com.aakhya.smartcall.application.admin.entity.ProcessUnitPK;
import com.aakhya.smartcall.application.admin.entity.RecordStatusType;
import com.aakhya.smartcall.application.admin.repository.ProcessUnitRepository;

@Service
public class ProcessUnitService {
	
	@Autowired
	private ProcessUnitRepository processUnitRepository;
	@Autowired
	private SequenceService sequenceService;
	
	
public List<ProcessUnit> findAllProcessUnits(){
		List<ProcessUnit> processUnits = processUnitRepository.findAll();
		return processUnits;
	}

public List<ProcessUnit> findAllProcessUnits(String processUnitNameFilter){
	List<ProcessUnit> processUnits = new ArrayList<ProcessUnit>();
	if(null == processUnitNameFilter || processUnitNameFilter.isEmpty()) {
		processUnits = processUnitRepository.findAll();
	}else {
		processUnits = processUnitRepository.search(processUnitNameFilter);
	}
	return processUnits;
}



public ProcessUnit findByPrimaryKey(ProcessUnitPK processUnitPK) {
	Optional<ProcessUnit> op = processUnitRepository.findById(processUnitPK);
	if(op.isPresent()) {
		ProcessUnit pu = op.get();
		return pu;
	}
	else {
		return null;
	}
}


public void saveProcessUnit(ProcessUnit processUnit) {
	if(null!= processUnit) {
		if(null==processUnit.getProcessingUnitid()) {
			Long processUnitId = sequenceService.getNewSequence(EntityNameType.PROCESS_UNIT, processUnit.getProcessingUnitid());
			processUnit.setProcessingUnitid(processUnitId);
			//processUnit.setCompanyId(1L);
		}		
		processUnitRepository.save(processUnit);
		}	
}


public void deleteProcessUnit(ProcessUnit processUnit) {
	if(null!= processUnit) {
		processUnit.setStatus(RecordStatusType.DELETED.getValue());
		processUnitRepository.delete(processUnit);//for test purpose to delete
		//processUnitRepository.save(processUnit);
		}	
}



}
