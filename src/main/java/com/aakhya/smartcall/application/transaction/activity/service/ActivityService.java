package com.aakhya.smartcall.application.transaction.activity.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aakhya.smartcall.application.admin.entity.EntityNameType;
import com.aakhya.smartcall.application.admin.entity.RecordStatusType;
import com.aakhya.smartcall.application.admin.service.SequenceService;
import com.aakhya.smartcall.application.security.entity.User;
import com.aakhya.smartcall.application.transaction.activity.entity.Activity;
import com.aakhya.smartcall.application.transaction.activity.repository.ActivityJdbcRepository;
import com.aakhya.smartcall.application.transaction.activity.repository.ActivityRepository;
import com.aakhya.smartcall.application.transaction.data.entity.TransactionDataSet;

@Service
public class ActivityService {

	@Autowired
	private ActivityRepository activityRepository;
	@Autowired
	private ActivityJdbcRepository activityJdbcRepository;
	@Autowired
	private SequenceService sequenceService;

	public void assignToUser(List<TransactionDataSet> transactionDataSets, User branchMgrUser, String assignedToUserId,
			User userToAssign) {
		if (null != transactionDataSets && !transactionDataSets.isEmpty()) {
			if (null != assignedToUserId && assignedToUserId.length() > 0) {
				for (TransactionDataSet transactionDataSet : transactionDataSets) {
					Activity existingActivity = activityRepository
							.findActivityAssigedTo(transactionDataSet.getDataSetId(), assignedToUserId, "PENDING");
					existingActivity.setActivityStatus("RE-ASSIGNED");
					activityRepository.save(existingActivity);
				}
			}
			for (TransactionDataSet transactionDataSet : transactionDataSets) {
				Activity activity = new Activity();
				Date eot = null;
				Long activityId = sequenceService.getNewSequence(EntityNameType.ACTIVITY, 1L);
				activity.setActivityId(activityId);
				activity.setDataSetId(transactionDataSet.getDataSetId());
				activity.setActivityType(1001L);
				activity.setActivityDescription("ASSIGN");
				activity.setActivityDateTime(new Date());
				activity.setCreatedBy(branchMgrUser.getUserId());
				activity.setCreatedDateTime(new Date());
				try {
					eot = new SimpleDateFormat("yyyy-MM-dd").parse("2200-01-01");
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				activity.setRemoveDateTime(eot);
				activity.setValidFrom(new Date());
				activity.setValidTo(eot);
				activity.setCompanyId(1L);
				activity.setStatus(RecordStatusType.ACTIVE.getValue());
				activity.setUserId(userToAssign.getUserId());
				activity.setBranchCode(transactionDataSet.getBranchCode());
				activity.setActivityStatus("PENDING");
				activityRepository.save(activity);
			}
		}
	}

}
