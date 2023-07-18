package com.aakhya.smartcall.application.security.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aakhya.smartcall.application.admin.entity.EntityNameType;
import com.aakhya.smartcall.application.admin.entity.RecordStatusType;
import com.aakhya.smartcall.application.admin.service.SequenceService;
import com.aakhya.smartcall.application.security.entity.Role;
import com.aakhya.smartcall.application.security.repository.RoleRepository;

@Service
public class RoleService {

	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private SequenceService sequenceService;
	
	public List<Role> findAll(String roleDescriptionFilter){
		if(null == roleDescriptionFilter || roleDescriptionFilter.isEmpty()) {
			return roleRepository.findAllRoles();
		}else {
			return roleRepository.search(roleDescriptionFilter);
		}
	}
	
	public void save(Role role) {
		if(null != role) {
			role.setCompanyId(1L);
			role.setStatus(RecordStatusType.ACTIVE.getValue());
			if(null == role.getRoleId()) {
				Long roleId = sequenceService.getNewSequence(EntityNameType.ROLE, role.getCompanyId());
				role.setRoleId(roleId);
			}
			roleRepository.save(role);
		}
	}
	
	public void delete(Role role) {
		if(null != role) {
			role.setStatus(RecordStatusType.DELETED.getValue());
			roleRepository.save(role);
		}
	}
	
	public void deleteRoles(Set<Role> roles) {
		if(null != roles && !roles.isEmpty()) {
			for(Role role:roles) {
				role.setStatus(RecordStatusType.DELETED.getValue());
				roleRepository.save(role);
			}
		}
	}
}
