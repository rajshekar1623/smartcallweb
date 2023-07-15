package com.aakhya.smartcall.application.admin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aakhya.smartcall.application.admin.entity.Branch;
import com.aakhya.smartcall.application.admin.entity.RecordStatusType;
import com.aakhya.smartcall.application.admin.repository.BranchRepository;

@Service
public class BranchService {

	@Autowired
	private BranchRepository branchRepository;
	
	public List<Branch> findAllBranches(String branchNameFilter){
		List<Branch> branches = new ArrayList<Branch>();
		if(null == branchNameFilter || branchNameFilter.isEmpty()) {
			branches = branchRepository.findAll();
		}else {
			branches = branchRepository.search(branchNameFilter);
		}
		return branches;
	}
	
	public List<Branch> findBranchesByHirearchy(String branchNameFilter){
		List<Branch> hirearchicialBranches = new ArrayList<Branch>();
		if(null == branchNameFilter || branchNameFilter.isEmpty()) {
			hirearchicialBranches =  branchRepository.findTopLevelBranches();
			if(null != hirearchicialBranches && !hirearchicialBranches.isEmpty()) {
				for(Branch parentBranch : hirearchicialBranches) {
					parentBranch.setChildBranches(getChildBranches(parentBranch));
				}
			}
		}else {
			hirearchicialBranches =   branchRepository.findTopLevelBranches(branchNameFilter);
			if(null == hirearchicialBranches || hirearchicialBranches.isEmpty()) {
				for(Branch parentBranch : hirearchicialBranches) {
					parentBranch.setChildBranches(getChildBranches(parentBranch,branchNameFilter));
				}
			}
		}
		return hirearchicialBranches;
	}
	
	public List<Branch> getChildBranches(Branch parentBranch){
		List<Branch> childBranches = branchRepository.findBranchesByHirearchy(parentBranch.getBranchCode());
		if(null != childBranches && ! childBranches.isEmpty()) {
			for(Branch childBranch:childBranches) {
				childBranch.setChildBranches(getChildBranches(childBranch));
			}
		}
		return childBranches;
	}
	
	private List<Branch> getChildBranches(Branch parentBranch,String branchNameFilter){
		List<Branch> childBranches = branchRepository.findBranchesByHirearchy(parentBranch.getBranchCode(),branchNameFilter);
		if(null != childBranches && ! childBranches.isEmpty()) {
			for(Branch childBranch:childBranches) {
				childBranch.setChildBranches(getChildBranches(childBranch,branchNameFilter));
			}
		}
		return childBranches;
	}
	
	public void saveBranch(Branch branch) {
		if(null != branch && null != branch.getBranchCode()
				&& !branch.getBranchCode().isEmpty()) {
			branch.setCompanyId(1L);
			branch.setStatus(RecordStatusType.ACTIVE.getValue());
			branchRepository.save(branch);
		}
	}
	
	public void deleteBranch(Branch branch) {
		if(null != branch && null != branch.getBranchCode()
				&& !branch.getBranchCode().isEmpty()) {
			branch.setStatus(RecordStatusType.DELETED.getValue());
			branchRepository.save(branch);
		}
	}
	
	public List<Branch> findClusters(){
		List<Branch> clusters = new ArrayList<Branch>();
		clusters.addAll(branchRepository.findBranchesByType(6L));
		clusters.addAll(branchRepository.findBranchesByType(7L));
		return clusters;
	}
}
