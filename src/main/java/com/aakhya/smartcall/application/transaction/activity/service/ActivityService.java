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
import com.aakhya.smartcall.application.transaction.activity.entity.ActivityDetail;
import com.aakhya.smartcall.application.transaction.activity.entity.ActivityWithDetail;
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
					boolean isParentAndExisitngSame = false;
					Activity parentActivity = activityRepository
							.findActivityAssigedTo(transactionDataSet.getDataSetId(), assignedToUserId);
					Activity existingActivity = null;
					if (null != parentActivity && "IN-PROCESS".equals(parentActivity.getActivityStatus())) {
						existingActivity = activityRepository.findPendingActivity(transactionDataSet.getDataSetId(),
								assignedToUserId);
					} else {
						isParentAndExisitngSame = true;
						existingActivity = parentActivity;
					}
					if (null != existingActivity) {
						Activity reassignedActivity = new Activity();
						Date eot = null;
						Long activityId = sequenceService.getNewSequence(EntityNameType.ACTIVITY, 1L);
						reassignedActivity.setActivityId(activityId);
						reassignedActivity.setDataSetId(existingActivity.getDataSetId());
						reassignedActivity.setActivityType(existingActivity.getActivityType());
						reassignedActivity.setActivityDescription(existingActivity.getActivityDescription());
						reassignedActivity.setActivityDateTime(new Date());
						reassignedActivity.setCreatedBy(branchMgrUser.getUserId());
						reassignedActivity.setCreatedDateTime(new Date());
						try {
							eot = new SimpleDateFormat("yyyy-MM-dd").parse("2200-01-01");
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						reassignedActivity.setRemoveDateTime(eot);
						reassignedActivity.setValidFrom(new Date());
						reassignedActivity.setValidTo(eot);
						reassignedActivity.setCompanyId(1L);
						reassignedActivity.setStatus(RecordStatusType.ACTIVE.getValue());
						reassignedActivity.setUserId(userToAssign.getUserId());
						reassignedActivity.setBranchCode(existingActivity.getBranchCode());
						reassignedActivity.setActivityStatus(existingActivity.getActivityStatus());
						reassignedActivity.setParentActivity(existingActivity.getParentActivity());
						activityRepository.save(reassignedActivity);
						if (isParentAndExisitngSame) {
							parentActivity.setActivityStatus("RE-ASSIGNED");
							activityRepository.save(parentActivity);
						} else {
							existingActivity.setActivityStatus("RE-ASSIGNED");
							activityRepository.save(existingActivity);
						}
					}
				}
			} else {
				for (TransactionDataSet transactionDataSet : transactionDataSets) {
					Activity alreadyAssigned = activityRepository
							.findParentActivityByDataSetId(transactionDataSet.getDataSetId());
					if (null == alreadyAssigned) {
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
	}

	public List<Activity> findActivityByDataSetId(Long dataSetId) {
		List<Activity> activities = activityRepository.findActivityByDataSetId(dataSetId);
		if (null != activities && !activities.isEmpty()) {
			for (Activity activity : activities) {
				if (null != activity.getActivityDetails() && !activity.getActivityDetails().isEmpty()) {
					for (ActivityDetail detail : activity.getActivityDetails()) {
						detail.getActivityDateTime();
					}
				}
			}
		}
		return activities;
	}

	public List<ActivityWithDetail> findActivitiesWithDetail(Long dataSetId) {
		return activityJdbcRepository.findActivitiesWithDetail(dataSetId);
	}

}
