package com.aakhya.smartcall.application.transaction.activity.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.aakhya.smartcall.application.transaction.activity.entity.ActivityFromUI;

@Repository
public class ActivityJdbcRepository {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	public List<ActivityFromUI> getActivities(Map<String, Object> filters){
		List<ActivityFromUI> activities = new ArrayList<ActivityFromUI>();
		StringBuffer query = new StringBuffer();
		Map<String,Object> params = new HashMap<String, Object>();
		query.append("select activityId,companyId,activityType,activityDescription,");
		query.append("activityDateTime,dataSetId,parentActivity,branchCode,userId,");
		query.append("attemptSequence,attemptDateTime,attemptDuration,attemptStatus,");
		query.append("attemptFlow,attemptNotes,scheduleType,scheduleDateTime,status ");
		query.append(" from sc_activity act join sc_activityDetail actDet");
		query.append(" on(act.activityId = actDet.activityId and act.companyId = actDet.companyId");
		for(String key:filters.keySet()) {
			if("userId".equals(key)) {
				query.append(" and act.userId = :userId");
				params.put(key,filters.get(key));
			}
			if("branchCode".equals(key)) {
				query.append(" and act.brachCode = :branchCode");
				params.put(key,filters.get(key));
			}
		}
//		query.append("");
		try {
			activities = namedParameterJdbcTemplate.query(query.toString(), params,new ActivityFromUIRowMapper());
		} catch (DataAccessException e) {
			activities = null;
		}
		return activities;
	}
}