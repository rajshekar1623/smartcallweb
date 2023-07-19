package com.aakhya.smartcall.application.transaction.activity.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

import com.aakhya.smartcall.application.transaction.activity.entity.ActivityFromUI;

public class ActivityFromUIRowMapper implements RowMapper<ActivityFromUI> {

	@Override
	public ActivityFromUI mapRow(ResultSet rs, int rowNum) throws SQLException {
		ActivityFromUI activityFromUI = new ActivityFromUI();
		activityFromUI.setActivityId(rs.getLong("activityId"));
		activityFromUI.setActivityDescription(rs.getString("activityDescription"));
		activityFromUI.setActivityDateTime(rs.getDate("activityDateTime"));
		activityFromUI.setActivityType(rs.getLong("activityType"));
		activityFromUI.setAttemptDateTime(rs.getDate("attemptDateTime"));
		activityFromUI.setAttemptDuration(rs.getInt("attemptDuration"));
		activityFromUI.setAttemptFlow(rs.getString("attemptFlow"));
		activityFromUI.setAttemptNotes(rs.getString("attemptNotes"));
		activityFromUI.setAttemptSequence(rs.getLong("attemptSequence"));
		activityFromUI.setAttemptStatus(rs.getString("attemptStatus"));
		Date scheduleDateTime = rs.getDate("scheduleDateTime");
		if(null != scheduleDateTime) {
			String scheduleDate = new SimpleDateFormat("dd-MM-yyyy")
					.format(scheduleDateTime);
			String ascheduleTime = new SimpleDateFormat("hh:mm a")
					.format(scheduleDateTime);
			activityFromUI.setScheduleDate(scheduleDate);
			activityFromUI.setScheduleTime(ascheduleTime);
		}
		activityFromUI.setScheduleType(rs.getString("scheduleType"));
		return activityFromUI;
	}

}
