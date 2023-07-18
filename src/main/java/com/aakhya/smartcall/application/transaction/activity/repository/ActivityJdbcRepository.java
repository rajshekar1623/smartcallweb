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
import com.aakhya.smartcall.application.transaction.activity.entity.ActivityWithDetail;

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
}