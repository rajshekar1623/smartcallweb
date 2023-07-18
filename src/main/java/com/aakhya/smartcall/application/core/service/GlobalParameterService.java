package com.aakhya.smartcall.application.core.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aakhya.smartcall.application.core.entity.GlobalParameter;
import com.aakhya.smartcall.application.core.entity.GlobalParameterPK;
import com.aakhya.smartcall.application.core.repository.GlobalParameterRepository;

@Service
public class GlobalParameterService {
	
	
	@Autowired
	private GlobalParameterRepository globalParameterRepository;

	
	public List<GlobalParameter> findAllGlobalParameters(){
		List<GlobalParameter> globalParameters = globalParameterRepository.findAll();
		
			return globalParameters;
	}
	
	
	public GlobalParameter findByPrimaryKey(GlobalParameterPK globalParameterPK){
		
		
		
		Optional<GlobalParameter> op = globalParameterRepository.findById(globalParameterPK);
		
		if(op.isPresent()) {
			
			GlobalParameter gp = op.get();
			
		return gp;	
		}
		
		else {
			return null;
		}
			
		
	}
	
	public void saveGlobalParameter(GlobalParameter globalParameter) {
		if(null != globalParameter) {
		globalParameterRepository.save(globalParameter);	
		}
		
		
	}
}