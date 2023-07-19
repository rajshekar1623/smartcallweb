package com.aakhya.smartcall.application.transaction.activity.service;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.aakhya.smartcall.application.transaction.activity.entity.ActivityFromUI;
import com.aakhya.smartcall.application.transaction.activity.entity.ActivityNote;
import com.aakhya.smartcall.application.transaction.activity.entity.ActivityStatus;
import com.aakhya.smartcall.application.transaction.activity.entity.ActivityType;
import com.aakhya.smartcall.application.transaction.activity.entity.ActivityTypeStr;
import com.aakhya.smartcall.application.transaction.activity.entity.ActivityWithDetail;
import com.aakhya.smartcall.application.transaction.activity.entity.CallDetails;
import com.aakhya.smartcall.application.transaction.activity.entity.ScheduleForTheDay;
import com.aakhya.smartcall.application.transaction.activity.entity.ScheduledVistit;
import com.aakhya.smartcall.application.transaction.activity.repository.ActivityJdbcRepository;
import com.aakhya.smartcall.application.transaction.activity.repository.ActivityRepository;
import com.aakhya.smartcall.application.transaction.data.entity.TransactionDataSet;

@Service
public class ActivityService {

	private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
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

	/**
	 * Below methods are for mobile application backend services
	 */
	
	public String submitCall(String flow, Long dataSetId, List<CallDetails> callDetails, String callingAgent,
			String scheduleDateTimeStr, String dateOfVisitPromisedStr, String foName, String relativeName,
			String relativeContactNumber,String reason,String amountCollected,String chequeDate,
			String chequeNumber,String chequeAmount,String bankName) {
		try {
			boolean isInProcess = false;
			@SuppressWarnings("unused")
			boolean inProcessVisit = false;
			boolean inProcessCall = false;
			System.out.println("22-May-2023 :: The flow from UI is :: " + flow);
			System.out.println("22-May-2023 :: The dataSetId from UI is :: " + dataSetId);
			Activity parentActivity = activityRepository.findActivityByStatus(ActivityStatus.PENDING.getValue(),
					dataSetId);
			Activity pendingScheduled = null;
			if(null == parentActivity) {
				parentActivity = activityRepository.findActivityByStatus(ActivityStatus.INPROCESS.getValue(),
						dataSetId);
				isInProcess = true;
				pendingScheduled = activityRepository.findPendingActivity(ActivityStatus.PENDING.getValue(),
						dataSetId);
				if(null != pendingScheduled) {
					if(null != pendingScheduled.getActivityDetails() 
							&& ! pendingScheduled.getActivityDetails().isEmpty()) {
						for(ActivityDetail activityDetail:pendingScheduled.getActivityDetails()) {
							if(ActivityTypeStr.CALL.getValue().equals(activityDetail.getScheduleType())){
								inProcessCall = true;
								break;
							}else if(ActivityTypeStr.VISIT.getValue().equals(activityDetail.getScheduleType())) {
								inProcessVisit = true;
								break;
							}
						}
					}
					pendingScheduled.setActivityStatus(ActivityStatus.COMPLETE.getValue());
				}
			}
			if ("STTC-RTP-SLFOP-FAP-UR-P-CNC".equals(flow) 
					|| "STTC-RTP-SLFOP-FAP-UR-P-CNTUD".equals(flow)
					|| "STTC-RTP-SLFOP-FAP-UR-P-CETBU".equals(flow) 
					|| "STTC-RTP-SLFOP-PAP-UR-P-CNC".equals(flow)
					|| "STTC-RTP-SLFOP-PAP-UR-P-CNTUD".equals(flow) 
					|| "STTC-RTP-SLFOP-PAP-UR-P-CETBU".equals(flow)) {
				parentActivity.setActivityStatus(ActivityStatus.COMPLETE.getValue());
				List<Activity> activities = new ArrayList<Activity>();
				activities.add(parentActivity);
				activities.add(prepareChildActivity(flow, dataSetId, callDetails, parentActivity, callingAgent,
						dateOfVisitPromisedStr, foName, relativeName, relativeContactNumber));
				activityRepository.saveAll(activities);
			} else if("VTC-OTH".equals(flow)) {
				parentActivity.setActivityStatus(ActivityStatus.COMPLETE.getValue());
				List<Activity> activities = new ArrayList<Activity>();
				activities.add(parentActivity);
				if(null != pendingScheduled) {
					pendingScheduled.setActivityDateTime(new Date());
					pendingScheduled.setActivityStatus(ActivityStatus.COMPLETE.getValue());
					activities.add(pendingScheduled);
				}
				activityRepository.saveAll(activities);
			} else if("STTC-OTH".equals(flow) || "VTC-NRTP-OTH".equals(flow) 
					|| "VTC-NRTP-NTL".equals(flow) || "STTC-NRTP-NTL".equals(flow)) {
				parentActivity.setActivityStatus(ActivityStatus.COMPLETE.getValue());
				List<Activity> activities = new ArrayList<Activity>();
				activities.add(parentActivity);
				if(inProcessCall)
				activities.add(prepareChildActivity(flow, dataSetId, callDetails, parentActivity, callingAgent,
						dateOfVisitPromisedStr, foName, relativeName, relativeContactNumber));
				else if(null != pendingScheduled) {
					pendingScheduled.setActivityStatus(ActivityStatus.COMPLETE.getValue());
					pendingScheduled.setActivityDateTime(new Date());
					pendingScheduled.setGenericString5(reason);
					activities.add(pendingScheduled);
				}
				activityRepository.saveAll(activities);
			} else if (null != flow && flow.startsWith("VTC-RTP-")) {
				System.out.println("****** Amount Collected is :: "+amountCollected);
				parentActivity.setActivityStatus(ActivityStatus.COMPLETE.getValue());
				List<Activity> activities = new ArrayList<Activity>();
				activities.add(parentActivity);
				if(inProcessCall)
				activities.add(prepareChildActivity(flow, dataSetId, callDetails, parentActivity, callingAgent,
						dateOfVisitPromisedStr, foName, relativeName, relativeContactNumber));
				else if(null != pendingScheduled) {
					pendingScheduled.setActivityStatus(ActivityStatus.COMPLETE.getValue());
					pendingScheduled.setActivityDateTime(new Date());
					if(null != amountCollected) {
						BigDecimal amtColl = new BigDecimal(amountCollected);
						pendingScheduled.setGenericDecimal1(amtColl);
					}
					if(null != chequeDate && !"".equals(chequeDate) && !chequeDate.isEmpty()) {
						Date cDate = new SimpleDateFormat("dd/MM/yyyy").parse(chequeDate);
						pendingScheduled.setGenericDate1(cDate);
					}
					if(null != chequeNumber && !"".equals(chequeNumber) && !chequeNumber.isEmpty())
						pendingScheduled.setGenericString1(chequeNumber);
					if(null != chequeAmount && !"".equals(chequeAmount) && !chequeAmount.isEmpty()) {
						BigDecimal chequeAmountDB = new BigDecimal(chequeAmount);
						pendingScheduled.setGenericDecimal2(chequeAmountDB);
					}
					if(null != bankName)
						pendingScheduled.setGenericString2(bankName);
					if(null != pendingScheduled.getActivityDetails() 
							&& ! pendingScheduled.getActivityDetails().isEmpty()) {
						String notes = null;
						Date attemptDateTime = null;
						if(null != callDetails && !callDetails.isEmpty()) {
							for(CallDetails callDetail:callDetails) {
								if(null != callDetail.getNotes() && !callDetail.getNotes().isEmpty()) {
									notes = callDetail.getNotes();
									if(null != callDetail.getCallDateTime() && !callDetail.getCallDateTime().isEmpty())
									attemptDateTime = df.parse(callDetail.getCallDateTime());
									break;
								}
							}
						}
						for(ActivityDetail det:pendingScheduled.getActivityDetails()) {
							det.setAttemptDateTime(attemptDateTime);
							det.setAttemptFlow(flow);
							det.setAttemptNotes(notes);
						}
					}
					activities.add(pendingScheduled);
				}
				activityRepository.saveAll(activities);
			}else if ("STTC-RTP-SLFOP-WPL".equals(flow) 
					|| "STTC-ATCL".equals(flow)
					|| "STTC-NRTP-WPL-UPDATE".equals(flow)) {
				parentActivity.setActivityStatus(ActivityStatus.INPROCESS.getValue());
				List<Activity> activities = new ArrayList<Activity>();
				activities.add(parentActivity);
				Activity childActivity = prepareChildActivity(flow, dataSetId, callDetails, parentActivity,
						callingAgent, dateOfVisitPromisedStr, foName, relativeName, relativeContactNumber);
				activities.add(childActivity);
				activities.add(prepareSchedule(childActivity, dataSetId, callingAgent, scheduleDateTimeStr,
						ActivityTypeStr.CALL));
				if(isInProcess && null != pendingScheduled)
					activities.add(pendingScheduled);
				activityRepository.saveAll(activities);
			} else if ("STTC-RTP-SVFC".equals(flow) ||
					"VTC-ATVL".equals(flow)) {
				parentActivity.setActivityStatus(ActivityStatus.INPROCESS.getValue());
				List<Activity> activities = new ArrayList<Activity>();
				activities.add(parentActivity);
				Activity childActivity = prepareChildActivity(flow, dataSetId, callDetails, parentActivity,
						callingAgent, dateOfVisitPromisedStr, foName, relativeName, relativeContactNumber);
				activities.add(childActivity);
				activities.add(prepareSchedule(childActivity, dataSetId, callingAgent, scheduleDateTimeStr,
						ActivityTypeStr.VISIT));
				activityRepository.saveAll(activities);
			} else if ("STTC-NRTP-WPL-WPLS".equals(flow)) {
				parentActivity.setActivityStatus(ActivityStatus.COMPLETE.getValue());
				List<Activity> activities = new ArrayList<Activity>();
				activities.add(parentActivity);
				Activity childActivity = prepareChildActivity(flow, dataSetId, callDetails, parentActivity,
						callingAgent, dateOfVisitPromisedStr, foName, relativeName, relativeContactNumber);
				activities.add(childActivity);
				if(isInProcess && null != pendingScheduled)
					activities.add(pendingScheduled);
				activityRepository.saveAll(activities);
			} else if ("STTC-NRTP-FNV".equals(flow)) {
				parentActivity.setActivityStatus(ActivityStatus.COMPLETE.getValue());
				List<Activity> activities = new ArrayList<Activity>();
				activities.add(parentActivity);
				Activity childActivity = prepareChildActivity(flow, dataSetId, callDetails, parentActivity,
						callingAgent, dateOfVisitPromisedStr, foName, relativeName, relativeContactNumber);
				activities.add(childActivity);
				if(isInProcess && null != pendingScheduled)
					activities.add(pendingScheduled);
				activityRepository.saveAll(activities);
			} else if ("STTC-NRTP-LTBR".equals(flow)) {
				parentActivity.setActivityStatus(ActivityStatus.COMPLETE.getValue());
				List<Activity> activities = new ArrayList<Activity>();
				activities.add(parentActivity);
				Activity childActivity = prepareChildActivity(flow, dataSetId, callDetails, parentActivity,
						callingAgent, dateOfVisitPromisedStr, foName, relativeName, relativeContactNumber);
				activities.add(childActivity);
				if(isInProcess && null != pendingScheduled)
					activities.add(pendingScheduled);
				activityRepository.saveAll(activities);
			} else if ("STTC-NRTP-AP".equals(flow)) {
				parentActivity.setActivityStatus(ActivityStatus.COMPLETE.getValue());
				List<Activity> activities = new ArrayList<Activity>();
				activities.add(parentActivity);
				Activity childActivity = prepareChildActivity(flow, dataSetId, callDetails, parentActivity,
						callingAgent, dateOfVisitPromisedStr, foName, relativeName, relativeContactNumber);
				activities.add(childActivity);
				if(isInProcess && null != pendingScheduled)
					activities.add(pendingScheduled);
				activityRepository.saveAll(activities);
			} else if("DNVTC-VR-CNA-US".equals(flow)
					|| "DNVTC-VR-LFV-US".equals(flow)) {
				List<Activity> activities = new ArrayList<Activity>();
				Activity pendingActivity = activityRepository.findPendingActivity(ActivityStatus.PENDING.getValue(), dataSetId);
				if(null != pendingActivity) {
					if(null != pendingScheduled.getActivityDetails() 
							&& ! pendingScheduled.getActivityDetails().isEmpty()) {
						String notes = null;
						Date attemptDateTime = null;
						if(null != callDetails && !callDetails.isEmpty()) {
							for(CallDetails callDetail:callDetails) {
								if(null != callDetail.getNotes() && !callDetail.getNotes().isEmpty()) {
									notes = callDetail.getNotes();
									if(null != callDetail.getCallDateTime())
									attemptDateTime = df.parse(callDetail.getCallDateTime());
									break;
								}
							}
						}
						for(ActivityDetail det:pendingScheduled.getActivityDetails()) {
							det.setAttemptDateTime(attemptDateTime);
							det.setAttemptFlow(flow);
							det.setAttemptNotes(notes);
						}
					}
					pendingActivity.setActivityStatus(ActivityStatus.COMPLETE.getValue());
					pendingActivity.setActivityDateTime(new Date());
					activities.add(prepareSchedule(pendingActivity, dataSetId, callingAgent, scheduleDateTimeStr,
							ActivityTypeStr.VISIT));
					activities.add(pendingActivity);
					activities.add(parentActivity);
					activityRepository.saveAll(activities);
				}
			} else if("DNVTC-VR-LFV-SNP".equals(flow) ||
					"DNVTC-VR-CNA-SNP".equals(flow) || 
					"DNVTC-VR-O".equals(flow) ||
					"DNVTC-PAM".equals(flow) ||
					"DNVTC-O".equals(flow)) {
				List<Activity> activities = new ArrayList<Activity>();
				parentActivity.setActivityStatus(ActivityStatus.COMPLETE.getValue());
				Activity pendingActivity = activityRepository.findPendingActivity(ActivityStatus.PENDING.getValue(), dataSetId);
				if(("DNVTC-VR-O".equals(flow) || "DNVTC-O".equals(flow)) && null != reason) 
					pendingActivity.setGenericString8(reason);
				pendingActivity.setActivityStatus(ActivityStatus.COMPLETE.getValue());
				pendingActivity.setActivityDateTime(new Date());
				activities.add(pendingActivity);
				activities.add(parentActivity);
				activityRepository.saveAll(activities);
			} else if("DNSTC-INV".equals(flow) || "DNSTC-NRB".equals(flow)
					|| "DNSTC-NSF".equals(flow)) {
				List<Activity> activities = new ArrayList<Activity>();
				parentActivity.setActivityStatus(ActivityStatus.COMPLETE.getValue());
				Activity pendingActivity = activityRepository.findPendingActivity(ActivityStatus.PENDING.getValue(), dataSetId);
				if(null != pendingActivity) {
					pendingActivity.setActivityStatus(ActivityStatus.COMPLETE.getValue());
					pendingActivity.setActivityDateTime(new Date());
					if(null != pendingActivity.getActivityDetails() && 
							pendingActivity.getActivityDetails().isEmpty()) {
						String notes = null;
						Date attemptDateTime = null;
						if(null != callDetails && !callDetails.isEmpty()) {
							for(CallDetails callDetail:callDetails) {
								if(null != callDetail.getNotes() && !callDetail.getNotes().isEmpty()) {
									notes = callDetail.getNotes();
									if(null != callDetail.getCallDateTime()) {
									try {
										System.out.println("*********"+callDetail.getCallDateTime());
										attemptDateTime = df.parse(callDetail.getCallDateTime());
									} catch (Exception e) {
										
									}
									}
									break;
								}
							}
						}
						for(ActivityDetail det:pendingScheduled.getActivityDetails()) {
							det.setAttemptDateTime(attemptDateTime);
							det.setAttemptFlow(flow);
							det.setAttemptNotes(notes);
						}
						activities.add(pendingActivity);
					}
				}
				activities.add(parentActivity);
				activityRepository.saveAll(activities);
			} else  if (null != flow && flow.startsWith("NRTP-")) {
				List<Activity> activities = new ArrayList<Activity>();
				Activity pendingActivity = activityRepository.findPendingActivity(ActivityStatus.PENDING.getValue(), dataSetId);
				parentActivity.setActivityStatus(ActivityStatus.COMPLETE.getValue());
				if(null != pendingActivity) {
					pendingActivity.setActivityStatus(ActivityStatus.COMPLETE.getValue());
					pendingActivity.setActivityDateTime(new Date());
					if(null != pendingActivity.getActivityDetails() && 
							pendingActivity.getActivityDetails().isEmpty()) {
						for(ActivityDetail activityDetail:pendingActivity.getActivityDetails()) {
							activityDetail.setAttemptFlow(flow);
						}
					}
					activities.add(pendingActivity);
				}
				activities.add(parentActivity);
				activityRepository.saveAll(activities);
			}
			return "SUCCESS";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "FAILURE";
		}
	}

	private Activity prepareChildActivity(String flow, Long dataSetId, List<CallDetails> callDetails,
			Activity parentActivity, String callingAgent, String dateOfVisitPromisedStr, String foName,
			String relativeName, String relativeContactNumber) {
		Activity childActivity = new Activity();

		Long activityId = sequenceService.getNewSequence(EntityNameType.ACTIVITY, 1L);
		childActivity.setActivityId(activityId);
		childActivity.setDataSetId(dataSetId);
		childActivity.setParentActivity(parentActivity.getActivityId());
		if (null != callDetails && !callDetails.isEmpty()) {
			Integer finalCallDetailSeq = callDetails.size() - 1;
			CallDetails finalCallDetail = callDetails.get(finalCallDetailSeq);
			String callDateTimeStr = finalCallDetail.getCallDateTime();

			Date callDateTime;
			try {
				callDateTime = df.parse(callDateTimeStr);
				childActivity.setActivityDateTime(callDateTime);
			} catch (ParseException e) {
				childActivity.setActivityDateTime(new Date());
			}

		} else {
			childActivity.setActivityDateTime(new Date());
		}
		childActivity.setActivityType(ActivityType.CALL.getValue());
		childActivity.setActivityDescription("CALL");
		childActivity.setCompanyId(1L);
		childActivity.setActivityStatus(ActivityStatus.COMPLETE.getValue());
		childActivity.setBranchCode(parentActivity.getBranchCode());
		childActivity.setCreatedBy(callingAgent);
		childActivity.setCreatedDateTime(new Date());
		childActivity.setRemoveDateTime(parentActivity.getRemoveDateTime());
		childActivity.setUserId(callingAgent);
		childActivity.setValidFrom(new Date());
		childActivity.setValidTo(parentActivity.getValidTo());
		if ("STTC-NRTP-FNV".equals(flow) && null != dateOfVisitPromisedStr && dateOfVisitPromisedStr.length() > 0
				&& null != foName && foName.length() > 0) {
			Date dateOfVisitPromised;
			try {
				dateOfVisitPromised = new SimpleDateFormat("dd-MM-yyyy").parse(dateOfVisitPromisedStr);
				childActivity.setGenericDate1(dateOfVisitPromised);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			childActivity.setGenericString1(foName);
		}
		if ("STTC-NRTP-LTBR".equals(flow) && null != relativeName && relativeName.length() > 0
				&& null != relativeContactNumber && relativeContactNumber.length() > 0) {
			childActivity.setGenericString2(relativeName);
			childActivity.setGenericString3(relativeContactNumber);
		}
		if (null != callDetails && !callDetails.isEmpty()) {
			List<ActivityDetail> activityDetails = new ArrayList<ActivityDetail>();
			for (CallDetails callDetail : callDetails) {
				ActivityDetail activityDetail = new ActivityDetail();
				activityDetail.setActivityId(activityId);
				activityDetail.setActivityDateTime(childActivity.getActivityDateTime());
				activityDetail.setCreatedBy(childActivity.getCreatedBy());
				activityDetail.setCreatedDateTime(childActivity.getCreatedDateTime());
				activityDetail.setRemoveDateTime(childActivity.getRemoveDateTime());
				activityDetail.setValidFrom(childActivity.getValidFrom());
				activityDetail.setValidTo(childActivity.getValidTo());
				activityDetail.setAttemptSequence(callDetail.getAttemptNo());
				activityDetail.setAttemptFlow(flow);
				activityDetail.setAttemptDuration(callDetail.getCallDuration());
				activityDetail.setAttemptNotes(callDetail.getNotes());
				activityDetail.setAttemptStatus(ActivityStatus.COMPLETE.getValue());
				activityDetail.setStatus(RecordStatusType.ACTIVE.getValue());
				activityDetail.setCompanyId(1L);
				activityDetails.add(activityDetail);
			}
			childActivity.setActivityDetails(activityDetails);
		}
		return childActivity;
	}

	private Activity prepareSchedule(Activity parentActivity, Long dataSetId, String callingAgent,
			String scheduleDateTimeStr, ActivityTypeStr scheduleType) {
		Activity childActivity = new Activity();

		Long activityId = sequenceService.getNewSequence(EntityNameType.ACTIVITY, 1L);
		childActivity.setActivityId(activityId);
		childActivity.setDataSetId(dataSetId);
		childActivity.setParentActivity(parentActivity.getActivityId());
		childActivity.setActivityDateTime(parentActivity.getActivityDateTime());
		childActivity.setActivityType(ActivityType.SCHEDULE.getValue());
		childActivity.setActivityDescription(ActivityTypeStr.SCHEDULE.getValue());
		childActivity.setCompanyId(1L);
		childActivity.setActivityStatus(ActivityStatus.PENDING.getValue());
		childActivity.setBranchCode(parentActivity.getBranchCode());
		childActivity.setCreatedBy(callingAgent);
		childActivity.setCreatedDateTime(new Date());
		childActivity.setRemoveDateTime(parentActivity.getRemoveDateTime());
		childActivity.setUserId(callingAgent);
		childActivity.setValidFrom(new Date());
		childActivity.setValidTo(parentActivity.getValidTo());
		List<ActivityDetail> activityDetails = new ArrayList<ActivityDetail>();
		ActivityDetail activityDetail = new ActivityDetail();
		activityDetail.setActivityId(activityId);
		activityDetail.setActivityDateTime(childActivity.getActivityDateTime());
		activityDetail.setCreatedBy(childActivity.getCreatedBy());
		activityDetail.setCreatedDateTime(childActivity.getCreatedDateTime());
		activityDetail.setRemoveDateTime(childActivity.getRemoveDateTime());
		activityDetail.setValidFrom(childActivity.getValidFrom());
		activityDetail.setValidTo(childActivity.getValidTo());
		activityDetail.setAttemptStatus(ActivityStatus.PENDING.getValue());
		activityDetail.setStatus(RecordStatusType.ACTIVE.getValue());
		activityDetail.setAttemptSequence(1);
		activityDetail.setCompanyId(1L);
		try {
			Date scheduleDateTime = df.parse(scheduleDateTimeStr);
			activityDetail.setScheduleDateTime(scheduleDateTime);

			activityDetail.setScheduleType(scheduleType.getValue());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		activityDetails.add(activityDetail);
		childActivity.setActivityDetails(activityDetails);
		return childActivity;
	}

	public List<ScheduledVistit> findScheduledVisits(String userId, Date fromDate, Date toDate) {
		if (null != fromDate && null != toDate)
			return activityJdbcRepository.findScheduledVisits(userId, fromDate, toDate);
		else {
			return activityJdbcRepository.findScheduledVisits(userId);
		}
	}

	public List<ScheduleForTheDay> getScheduleForTheDay(String userId) {
		return activityJdbcRepository.getScheduleForTheDay(userId);
	}

	public List<ActivityFromUI> getCallsForTheDay(String userId) {
		return activityJdbcRepository.getCallsForTheDay(userId);
	}

	public List<ActivityFromUI> getVisitsForTheDay(String userId) {
		return activityJdbcRepository.getVisitsForTheDay(userId);
	}

	public List<com.aakhya.smartcall.application.transaction.activity.controller.Activity> findActivitiesForDataSetId(
			Long dataSetId, Date fromDate, Date toDate) {
		List<com.aakhya.smartcall.application.transaction.activity.controller.Activity> activitiesForUi = new ArrayList<>();
		List<Activity> activitiesFromService = null;
		if (null != fromDate & null != toDate) {
			activitiesFromService = activityRepository.findActivitesForDataSetId(dataSetId, fromDate, toDate);

		} else {
			activitiesFromService = activityRepository.findActivitesForDataSetId(dataSetId);
		}
		if (null != activitiesFromService && !activitiesFromService.isEmpty()) {
			for (Activity activityFromService : activitiesFromService) {
				com.aakhya.smartcall.application.transaction.activity.controller.Activity activityForUi = new com.aakhya.smartcall.application.transaction.activity.controller.Activity();
//				BeanUtils.copyProperties(activitiesFromService,activitiesForUi);
				activityForUi.setActivityId(activityFromService.getActivityId());
				activityForUi.setActivityDescription(activityFromService.getActivityDescription());
				activityForUi.setActivityStatus(activityFromService.getActivityStatus());
				activityForUi.setActivityType(activityFromService.getActivityType());
				activityForUi.setBranchCode(activityFromService.getBranchCode());
				activityForUi.setUserId(activityFromService.getUserId());
				if (null != activityFromService.getActivityDateTime()) {
					String activityDate = new SimpleDateFormat("dd-MM-yyyy")
							.format(activityFromService.getActivityDateTime());
					String activityTime = new SimpleDateFormat("hh:mm a")
							.format(activityFromService.getActivityDateTime());
					String dayOfWeek = new SimpleDateFormat("EEEE").format(activityFromService.getActivityDateTime());
					activityForUi.setActivityDate(activityDate);
					activityForUi.setActivityTime(activityTime);
					activityForUi.setDay(dayOfWeek);
				}
				if(null != activityFromService.getScheduledDateTime()) {
					String scheduleDate = new SimpleDateFormat("dd-MM-yyyy").format(activityFromService.getScheduledDateTime());
					String scheduleTime = new SimpleDateFormat("hh:mm a").format(activityFromService.getScheduledDateTime());
					activityForUi.setScheduleDate(scheduleDate);
					activityForUi.setScheduleTime(scheduleTime);
				}
				if(null != activityFromService.getActivityDetails() 
						&& !activityFromService.getActivityDetails().isEmpty()) {
					List<com.aakhya.smartcall.application.transaction.activity.controller.ActivityDetail> activityDetailsForUi = 
							new ArrayList<com.aakhya.smartcall.application.transaction.activity.controller.ActivityDetail>();
					for(ActivityDetail activityDetailFromService:activityFromService.getActivityDetails()) {
						com.aakhya.smartcall.application.transaction.activity.controller.ActivityDetail activityDetailForUi = 
								new com.aakhya.smartcall.application.transaction.activity.controller.ActivityDetail();
						activityDetailForUi.setActivityId(activityDetailFromService.getActivityId());
						activityDetailForUi.setAttemptSequence(activityDetailFromService.getAttemptSequence());
						activityDetailForUi.setActivityDate(activityForUi.getActivityDate());
						activityDetailForUi.setActivityTime(activityForUi.getActivityTime());
						if(null != activityFromService.getGenericDecimal1())
							activityForUi.setAmountCollected(activityFromService.getGenericDecimal1().toPlainString());
						if(null != activityFromService.getGenericDecimal2())
						activityForUi.setChequeAmount(activityFromService.getGenericDecimal2().toPlainString());
						if(null != activityFromService.getGenericDate1()) {
							String chequeDt = new SimpleDateFormat("dd-MM-yyyy")
									.format(activityFromService.getGenericDate1());
							activityForUi.setChequeDate(chequeDt);
						}
						activityForUi.setChequeNumber(activityFromService.getGenericString1());
						if (null != activityDetailFromService.getAttemptDateTime()) {
							String attemptDate = new SimpleDateFormat("dd-MM-yyyy")
									.format(activityDetailFromService.getAttemptDateTime());
							String attemptTime = new SimpleDateFormat("hh:mm a")
									.format(activityDetailFromService.getAttemptDateTime());
							activityDetailForUi.setAttemptDate(attemptDate);
							activityDetailForUi.setAttemptTime(attemptTime);
						}
						if (null != activityDetailFromService.getScheduleDateTime()) {
							String scheduleDate = new SimpleDateFormat("dd-MM-yyyy")
									.format(activityDetailFromService.getScheduleDateTime());
							String ascheduleTime = new SimpleDateFormat("hh:mm a")
									.format(activityDetailFromService.getScheduleDateTime());
							activityDetailForUi.setScheduleDate(scheduleDate);
							activityDetailForUi.setScheduleTime(ascheduleTime);
						}
						activityDetailForUi.setAttemptFlow(activityDetailFromService.getAttemptFlow());
						activityDetailForUi.setAttemptNotes(activityDetailFromService.getAttemptNotes());
						activityDetailsForUi.add(activityDetailForUi);
					}
					activityForUi.setActivityDetails(activityDetailsForUi);
				}
				activitiesForUi.add(activityForUi);
			}
		}
		return activitiesForUi;
	}
	
	public List<ActivityNote> getNotesHistory(Long dataSetId){
		return activityJdbcRepository.getNotesHistory(dataSetId);
	}
	
}
