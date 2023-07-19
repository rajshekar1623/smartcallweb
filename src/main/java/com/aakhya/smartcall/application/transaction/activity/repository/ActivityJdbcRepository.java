package com.aakhya.smartcall.application.transaction.activity.repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.aakhya.smartcall.application.transaction.activity.entity.ActivityFromUI;
import com.aakhya.smartcall.application.transaction.activity.entity.ActivityNote;
import com.aakhya.smartcall.application.transaction.activity.entity.ActivityWithDetail;
import com.aakhya.smartcall.application.transaction.activity.entity.ScheduleForTheDay;
import com.aakhya.smartcall.application.transaction.activity.entity.ScheduledVistit;

@Repository
public class ActivityJdbcRepository {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public List<ActivityFromUI> getActivities(Map<String, Object> filters) {
		List<ActivityFromUI> activities = new ArrayList<ActivityFromUI>();
		StringBuffer query = new StringBuffer();
		Map<String, Object> params = new HashMap<String, Object>();
		query.append("select activityId,companyId,activityType,activityDescription,");
		query.append("activityDateTime,dataSetId,parentActivity,branchCode,userId,");
		query.append("attemptSequence,attemptDateTime,attemptDuration,attemptStatus,");
		query.append("attemptFlow,attemptNotes,scheduleType,scheduleDateTime,status ");
		query.append(" from sc_activity act join sc_activityDetail actDet");
		query.append(" on(act.activityId = actDet.activityId and act.companyId = actDet.companyId");
		for (String key : filters.keySet()) {
			if ("userId".equals(key)) {
				query.append(" and act.userId = :userId");
				params.put(key, filters.get(key));
			}
			if ("branchCode".equals(key)) {
				query.append(" and act.brachCode = :branchCode");
				params.put(key, filters.get(key));
			}
		}
//		query.append("");
		try {
			activities = namedParameterJdbcTemplate.query(query.toString(), params, new ActivityFromUIRowMapper());
		} catch (DataAccessException e) {
			activities = null;
		}
		return activities;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<ActivityWithDetail> findActivitiesWithDetail(Long dataSetId) {
		List<ActivityWithDetail> activities = new ArrayList<ActivityWithDetail>();
		StringBuffer query = new StringBuffer();
		query.append("select a.activityDateTime,dbo.fn_getUserName(a.userId,1) userName,");
		query.append(" b.scheduleType,dbo.getScheduleDateTime(a.activityId) scheduleDateTime,b.attemptFlow,a.activityDescription,a.genericDecimal1");
		query.append(" from sc_activity a join sc_activityDetail b");
		query.append(" on(a.activityId = b.activityId and a.dataSetId = :dataSetId ");
		query.append(" and a.parentActivity is not null and (activityType <> 1004 or b.attemptFlow is not null)) order by a.activityId desc");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("dataSetId", dataSetId);
		activities = namedParameterJdbcTemplate.query(query.toString(), paramMap, new RowMapper() {

			@Override
			public ActivityWithDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
				ActivityWithDetail activityWithDetail = new ActivityWithDetail();
				Date activityDateTime = rs.getTimestamp("activityDateTime");
				if (null != activityDateTime) {
					Calendar cal = Calendar.getInstance();
					cal.setTime(activityDateTime);
					int dayNo = cal.get(Calendar.DAY_OF_WEEK);
					String day = null;
					switch (dayNo) {
					case 1:
						System.out.println("Monday");
						break;
					case 2:
						System.out.println("Tuesday");
						break;
					case 3:
						System.out.println("Wednesday");
						break;
					case 4:
						System.out.println("Thursday");
						break;
					case 5:
						System.out.println("Friday");
						break;
					case 6:
						System.out.println("Saturday");
						break;
					case 7:
						System.out.println("Sunday");
						break;
					}
					activityWithDetail.setActivityDay(day);
					activityWithDetail.setActivityDate(new SimpleDateFormat("dd-MM-yyyy").format(activityDateTime));
					activityWithDetail.setActivityTime(new SimpleDateFormat("hh:mm a").format(activityDateTime));
					activityWithDetail.setAmountCollected(rs.getString("genericDecimal1"));
				}
				activityWithDetail.setUserName(rs.getString("userName"));
				activityWithDetail.setScheduleType(rs.getString("scheduleType"));
				activityWithDetail.setActivityType(rs.getString("activityDescription"));
				String flow = rs.getString("attemptFlow");
				if (null != flow) {
					String[] flowSteps = flow.split("-");
					if (null != flowSteps && flowSteps.length > 0) {
						List<String> steps = new ArrayList<String>();
						for (int i = 0; i < flowSteps.length; i++) {
							String step = flowSteps[i];
							switch (step) {
							case "STTC":
								steps.add("Spoke to the customer");
								break;
							case "RTP":
								steps.add("Ready to pay");
								break;
							case "NRTP":
								steps.add("Not ready to pay");
								break;
							case "ATCL":
								steps.add("Asked to call later");
								break;
							case "AP":
								steps.add("Already paid");
								break;
							case "WPL":
								steps.add("Will pay later");
								break;
							case "SLFOP":
								steps.add("Send link for online payment");
								break;
							case "FAP":
								steps.add("Full amount paid");
								break;
							case "PAP":
								steps.add("Partial amount paid");
								break;
							case "SVFC":
								steps.add("Schedule visit for collection");
								break;
							case "DNVTC":
								steps.add("Did not visit the customer");
								break;
							case "VR":
								steps.add("Visit Rescheduled");
								break;
							case "CNA":
								steps.add("Customer not available");
								break;
							case "LFV":
								steps.add("Late for visit");
								break;
							case "CAP":
								steps.add("Cash payment");
								break;
							case "CHP":
								steps.add("Cheque payment");
								break;
							}
						}
						Date scheduleDateTime = rs.getTimestamp("scheduleDateTime");
						if (null != scheduleDateTime) {
							String scheduledDateTime = new SimpleDateFormat("dd-MM-yyyy hh:mm a")
									.format(scheduleDateTime);
							activityWithDetail.setScheduleDateTime(scheduledDateTime);

							steps.add(scheduledDateTime);
						}
						activityWithDetail.setSteps(steps);
					}
				}
				return activityWithDetail;
			}
		});
		return activities;
	}
	
	/**
	 * 
	 */
	
	public List<ScheduledVistit> findScheduledVisits(String userId){
		List<ScheduledVistit> scheduledVisits = new ArrayList<ScheduledVistit>();
		StringBuffer query = new StringBuffer();
		query.append("select a.dataSetId,dbo.fn_getName(a.dataSetId) memberName,case a.dataSetType when 1 then 'Marketing' when 2 then 'NPA'");
		query.append(" when 3 then 'Welcome Call' end queue,d.scheduleDateTime,dbo.fn_getGenericClassifier(a.village,7) village  from sc_transactionDataset a join sc_activity c ");
		query.append(" on(a.dataSetId = c.dataSetId) join sc_activityDetail d on(c.activityId = d.activityId) ");
		query.append(" where c.activityType = 1004 and d.scheduleType = 'VISIT' and (convert(varchar,d.scheduleDateTime,105) = convert(varchar,getDate(),105) or d.scheduleDateTime > getDate())  and c.userId = :userId order by d.scheduleDateTime");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		scheduledVisits = namedParameterJdbcTemplate.query(query.toString(), params,new RowMapper<ScheduledVistit>() {

			@Override
			public ScheduledVistit mapRow(ResultSet rs, int rowNum) throws SQLException {
				ScheduledVistit scheduledVisit = new ScheduledVistit();
				scheduledVisit.setDataSetId(rs.getLong("dataSetId"));
				Date scheduledDateTime = rs.getTimestamp("scheduleDateTime");
				System.out.println(scheduledDateTime);
				if(null != scheduledDateTime) {
					String date = new SimpleDateFormat("dd-MMM-yyyy").format(scheduledDateTime);
					String time = new SimpleDateFormat("hh:mm a").format(scheduledDateTime);
					scheduledVisit.setScheduledDate(date);
					scheduledVisit.setScheduledTime(time);
				}
				scheduledVisit.setMemberName(rs.getString("memberName"));
				scheduledVisit.setQueue(rs.getString("queue"));
				scheduledVisit.setVillageName(rs.getString("village"));
				return scheduledVisit;
			}
		});
		return scheduledVisits;
	}
	
	public List<ScheduledVistit> findScheduledVisits(String userId,Date fromDate,Date toDate){
		List<ScheduledVistit> scheduledVisits = new ArrayList<ScheduledVistit>();
		StringBuffer query = new StringBuffer();
		query.append("select a.dataSetId,dbo.fn_getName(a.dataSetId) memberName,case a.dataSetType when 1 then 'Marketing' when 2 then 'NPA'");
		query.append(" when 3 then 'Welcome Call' end queue,d.scheduleDateTime,dbo.fn_getGenericClassifier(a.village,7) village  from sc_transactionDataset a join sc_activity c ");
		query.append(" on(a.dataSetId = c.dataSetId) join sc_activityDetail d on(c.activityId = d.activityId) ");
		query.append(" where c.activityType = 1004 and d.scheduleType = 'VISIT' and d.scheduleDateTime  between :fromDate and :toDate  and c.userId = :userId order by d.scheduleDateTime");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		params.put("fromDate", fromDate);
		params.put("toDate", toDate);
		scheduledVisits = namedParameterJdbcTemplate.query(query.toString(), params,new RowMapper<ScheduledVistit>() {

			@Override
			public ScheduledVistit mapRow(ResultSet rs, int rowNum) throws SQLException {
				ScheduledVistit scheduledVisit = new ScheduledVistit();
				scheduledVisit.setDataSetId(rs.getLong("dataSetId"));
				Date scheduledDateTime = rs.getTimestamp("scheduleDateTime");
				if(null != scheduledDateTime) {
					String date = new SimpleDateFormat("dd-MMM-yyyy").format(scheduledDateTime);
					String time = new SimpleDateFormat("hh:mm a").format(scheduledDateTime);
					scheduledVisit.setScheduledDate(date);
					scheduledVisit.setScheduledTime(time);
				}
				scheduledVisit.setMemberName(rs.getString("memberName"));
				scheduledVisit.setQueue(rs.getString("queue"));
				scheduledVisit.setVillageName(rs.getString("village"));
				return scheduledVisit;
			}
		});
		return scheduledVisits;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<ScheduleForTheDay> getScheduleForTheDay(String userId) {
		List<ScheduleForTheDay> schedulesForTheDay = new ArrayList<ScheduleForTheDay>();
		StringBuffer query = new StringBuffer();
		query.append(" select 'Call' queue,count(distinct a.dataSetId) pendingCnt,count(distinct b.dataSetId) completeCnt,usr.userId ");
		query.append(" from sc_user usr left outer join (select a.datasetId,a.activityId,a.userId from sc_activity a join sc_activityDetail b ");
		query.append(" on(a.activityId = b.activityId and a.activityType = 1004 and b.scheduleType = 'CALL'  ");
		query.append(" and convert(varchar,b.scheduleDateTime,105)  = convert(varchar,getDate(),105) and a.activityStatus = 'PENDING' ) ");
		query.append(" ) A on (usr.userId = A.userId) left outer join (select a.datasetId,a.activityId,a.userId from sc_activity a join sc_activityDetail b ");
		query.append(" on(a.activityId = b.activityId and a.activityType = 1004 and b.scheduleType = 'CALL'  ");
		query.append(" and convert(varchar,b.scheduleDateTime,105)  = convert(varchar,getDate(),105) and a.activityStatus = 'COMPLETE' ) ");
		query.append(" ) B on (usr.userId = B.userId) where usr.userid = :userId group by usr.userid ");
		query.append(" union ");
		query.append(" select 'Visit' queue,count(distinct a.dataSetId) pendingCnt,count(distinct b.dataSetId) completeCnt,usr.userId  ");
		query.append(" from sc_user usr left outer join (select a.datasetId,a.activityId,a.userId from sc_activity a join sc_activityDetail b ");
		query.append(" on(a.activityId = b.activityId and a.activityType = 1004 and b.scheduleType = 'VISIT'  ");
		query.append(" and convert(varchar,b.scheduleDateTime,105)  = convert(varchar,getDate(),105) and a.activityStatus = 'PENDING' ) ");
		query.append(" ) A on (usr.userId = A.userId) left outer join (select a.datasetId,a.activityId,a.userId from sc_activity a join sc_activityDetail b ");
		query.append(" on(a.activityId = b.activityId and a.activityType = 1004 and b.scheduleType = 'VISIT'  ");
		query.append(" and convert(varchar,b.scheduleDateTime,105)  = convert(varchar,getDate(),105) and a.activityStatus = 'COMPLETE' ) ");
		query.append(" ) B on (usr.userId = B.userId) where usr.userid = :userId group by usr.userid ");
//		System.out.println("***** the query being executed is :: "+query.toString().replaceAll(":userId", "'CA_01_001'"));
		Map<String, Object> params = new HashMap<String,Object>();
		params.put("userId", userId);
		schedulesForTheDay = namedParameterJdbcTemplate.query(query.toString(), params, new RowMapper() {

			@Override
			public ScheduleForTheDay mapRow(ResultSet rs, int rowNum) throws SQLException {
				ScheduleForTheDay scheduleForTheDay = new ScheduleForTheDay();
				scheduleForTheDay.setQueue(rs.getString("queue"));
				scheduleForTheDay.setPending(rs.getInt("pendingCnt"));
				scheduleForTheDay.setComplete(rs.getInt("completeCnt"));
				return scheduleForTheDay;
			}
		});
		return schedulesForTheDay;
	}
	
	public List<ActivityFromUI> getCallsForTheDay(String userId) {
		List<ActivityFromUI> callsForTheDay = new ArrayList<ActivityFromUI>();
		StringBuffer query = new StringBuffer();
		query.append(" select a.activityId,a.companyId,a.activityType,a.activityDescription,");
		query.append(" a.activityDateTime,a.dataSetId,a.parentActivity,a.branchCode,a.userId,");
		query.append(" b.attemptSequence,b.attemptDateTime,b.attemptDuration,b.attemptStatus,");
		query.append(" b.attemptFlow,b.attemptNotes,b.scheduleType,b.scheduleDateTime,a.status ");
		query.append(" from sc_activity a join sc_activityDetail b ");
		query.append(" on(a.activityId = b.activityId and a.activityType = 1004 ");
		query.append(" and b.scheduleType = 'CALL' and convert(varchar,b.scheduleDateTime,105) ");
		query.append(" = convert(varchar,getDate(),105) and a.activityStatus = 'PENDING' ) ");
		Map<String, Object> params = new HashMap<String,Object>();
		params.put("userId", userId);
		callsForTheDay = namedParameterJdbcTemplate.query(query.toString(), params, new ActivityFromUIRowMapper());
		return callsForTheDay;
	}
	
	public List<ActivityFromUI> getVisitsForTheDay(String userId) {
		List<ActivityFromUI> callsForTheDay = new ArrayList<ActivityFromUI>();
		StringBuffer query = new StringBuffer();
		query.append(" select a.activityId,a.companyId,a.activityType,a.activityDescription,");
		query.append(" a.activityDateTime,a.dataSetId,a.parentActivity,a.branchCode,a.userId,");
		query.append(" b.attemptSequence,b.attemptDateTime,b.attemptDuration,b.attemptStatus,");
		query.append(" b.attemptFlow,b.attemptNotes,b.scheduleType,b.scheduleDateTime,a.status ");
		query.append(" from sc_activity a join sc_activityDetail b ");
		query.append(" on(a.activityId = b.activityId and a.activityType = 1004 ");
		query.append(" and b.scheduleType = 'VISIT' and convert(varchar,b.scheduleDateTime,105) ");
		query.append(" = convert(varchar,getDate(),105) and a.activityStatus = 'PENDING' ) ");
		Map<String, Object> params = new HashMap<String,Object>();
		params.put("userId", userId);
		callsForTheDay = namedParameterJdbcTemplate.query(query.toString(), params, new ActivityFromUIRowMapper());
		return callsForTheDay;
	}
	
//	public List<Activity> findActivitiesForDataSet(Long dataSetId,Date fromDate,Date toDate){
//		StringBuffer query = new StringBuffer();
//		query.append(" select a.activityId,a.companyId,a.activityType,a.activityDescription,");
//		query.append(" a.dataSetId,a.parentActivity,");
//		rivate Long ;
//		private Long ;
//		private Long ;
//		private String ;
//		private String activityDate;
//		private String activityTime;
//		private String day;
//		private Long ;
//		private Long ;
//		private String branchCode;
//		private String userId;
//		private String userName;
//		private String activityStatus;
//		return null;
//	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<ActivityNote> getNotesHistory(Long dataSetId){
		List<ActivityNote> activityNotes = new ArrayList<ActivityNote>();
		StringBuffer query = new StringBuffer();
		query.append(" select usr.userName,act.activityDateTime,det.attemptNotes,act.activityDescription ");
		query.append(" from sc_activity act join sc_activityDetail det on(act.activityId = det.activityId ");
		query.append(" and dataSetId = :dataSetId) join sc_user usr on(act.createdBy = usr.userid) ");
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("dataSetId",dataSetId);
		activityNotes = namedParameterJdbcTemplate.query(query.toString(), param, new RowMapper() {

			@Override
			public ActivityNote mapRow(ResultSet rs, int rowNum) throws SQLException {
				ActivityNote activityNote = new ActivityNote();
				activityNote.setUserName(rs.getString("userName"));
				activityNote.setNotes(rs.getString("attemptNotes"));
				Date activityDateTime = rs.getTimestamp("activityDateTime");
				if(null != activityDateTime) {
					String activityDate = new SimpleDateFormat("dd-MM-yyyy").format(activityDateTime);
					String activityTime = new SimpleDateFormat("hh:mm a").format(activityDateTime);
					activityNote.setDate(activityDate);
					activityNote.setTime(activityTime);
				}
				activityNote.setActivityType(rs.getString("activityDescription"));
				return activityNote;
			}
		});
		return activityNotes;
	}
}