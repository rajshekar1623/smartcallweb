package com.aakhya.smartcall.application.transaction.activity.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aakhya.smartcall.application.transaction.activity.entity.ActivityFromUI;
import com.aakhya.smartcall.application.transaction.activity.entity.ActivityNote;
import com.aakhya.smartcall.application.transaction.activity.entity.CallDetails;
import com.aakhya.smartcall.application.transaction.activity.entity.ScheduleForTheDay;
import com.aakhya.smartcall.application.transaction.activity.entity.ScheduledVistit;
import com.aakhya.smartcall.application.transaction.activity.service.ActivityService;

@RestController
@RequestMapping("/activity")
public class ActivityController {

	@Autowired
	private ActivityService activityService;

	@RequestMapping(path = "/submitcall", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public String submitCall(@RequestParam("flow") String flow, @RequestParam("dataSetId") Long dataSetId,
			@RequestBody List<CallDetails> callDetails, @RequestParam("callingAgent") String callingAgent,
			@RequestParam("scheduledDateTime") String scheduledDateTimeStr,
			@RequestParam("dateOfVisitPromised") String dateOfVisitPromisedStr, @RequestParam("foName") String foName,
			@RequestParam("relativeName") String relativeName,
			@RequestParam("relativeContactNumber") String relativeContactNumber,
			@RequestParam("reason") String reason) {
		return activityService.submitCall(flow, dataSetId, callDetails, callingAgent, scheduledDateTimeStr,
				dateOfVisitPromisedStr, foName, relativeName, relativeContactNumber,reason,null,
				null,null,null,null);
	}
	
	@RequestMapping(path = "/submitcallCheque", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public String submitCallcheque(@RequestParam("flow") String flow, @RequestParam("dataSetId") Long dataSetId,
			@RequestBody List<CallDetails> callDetails, @RequestParam("callingAgent") String callingAgent,
			@RequestParam("scheduledDateTime") String scheduledDateTimeStr,
			@RequestParam("dateOfVisitPromised") String dateOfVisitPromisedStr, @RequestParam("foName") String foName,
			@RequestParam("relativeName") String relativeName,
			@RequestParam("relativeContactNumber") String relativeContactNumber,
			@RequestParam("amountCollected")String amountCollected,
			@RequestParam("chequeDate") String chequeDate,
			@RequestParam("chequeNumber")String chequeNumber,
			@RequestParam("chequeAmount") String chequeAmount,
			@RequestParam("bankName") String bankName) {
		return activityService.submitCall(flow, dataSetId, callDetails, callingAgent, scheduledDateTimeStr,
				dateOfVisitPromisedStr, foName, relativeName, relativeContactNumber,null,amountCollected,chequeDate,
				chequeNumber,chequeAmount,bankName);
	}

	@RequestMapping(path = "/scheduledVisits", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ScheduledVistit> findScheduledVisits(@RequestParam("userId") String userId,
			@RequestParam("fromDate") String fromDateStr, @RequestParam("toDate") String toDateStr) {
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		Date fromDate = null;
		Date toDate = null;
		if (null != fromDateStr && fromDateStr.length() > 0 && null != toDateStr && toDateStr.length() > 0) {
			try {
				if (null != fromDateStr && fromDateStr.length() > 0)
					fromDate = df.parse(fromDateStr);
				if (null != toDateStr && toDateStr.length() > 0)
					toDate = df.parse(toDateStr);
				Calendar c = Calendar.getInstance();
				c.setTime(toDate);
				c.add(Calendar.DATE, 1);
				toDate = c.getTime();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return activityService.findScheduledVisits(userId, fromDate, toDate);
	}

	public Map<Date, List<ScheduledVistit>> arrangeDates(List<ScheduledVistit> visits) {
		List<Date> dates = new ArrayList<Date>();
		for (ScheduledVistit visit : visits) {
			Date date;
			try {
				date = new SimpleDateFormat("dd-MMM-yyyy").parse(visit.getScheduledDate());
				dates.add(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		List<Date> sortedDates = dates.stream().sorted().collect(Collectors.toList());
		Map<Date, List<ScheduledVistit>> groupedVisits = new HashMap<Date, List<ScheduledVistit>>();
		for (Date sortedDate : sortedDates) {
			groupedVisits.put(sortedDate, new ArrayList<ScheduledVistit>());
		}
		for (ScheduledVistit scheduledVistit : visits) {
			Date date;
			try {
				date = new SimpleDateFormat("dd-MMM-yyyy").parse(scheduledVistit.getScheduledDate());
				groupedVisits.get(date).add(scheduledVistit);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return groupedVisits;
	}

	@RequestMapping(path = "/scheduledForTheDay", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ScheduleForTheDay> getScheduleForTheDay(@RequestParam("userId") String userId) {
		return activityService.getScheduleForTheDay(userId);
	}

	@RequestMapping(path = "/callsForTheDay", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ActivityFromUI> getCallsForTheDay(@RequestParam("userId") String userId) {
		return activityService.getCallsForTheDay(userId);
	}

	@RequestMapping(path = "/visitsForTheDay", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ActivityFromUI> getVisitsForTheDay(@RequestParam("userId") String userId) {
		return activityService.getVisitsForTheDay(userId);
	}

	@RequestMapping(path = "/activitiesForDataSet", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<com.aakhya.smartcall.application.transaction.activity.controller.Activity> findActivitiesForDataSetId(
			@RequestParam("dataSetId") Long dataSetId, @RequestParam("fromDate") String fromDateStr,
			@RequestParam("toDate") String toDateStr) {
		if (null != fromDateStr && fromDateStr.length() > 0 && null != toDateStr && toDateStr.length() > 0) {
			try {
				Date fromDate = new SimpleDateFormat("dd-MM-yyyy").parse(fromDateStr);
				Date toDate = new SimpleDateFormat("dd-MM-yyyy").parse(toDateStr);
				return activityService.findActivitiesForDataSetId(dataSetId, fromDate, toDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		} else
			return activityService.findActivitiesForDataSetId(dataSetId, null, null);
	}

	@RequestMapping(path = "/getNotesHistory", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ActivityNote> getNotesHistory(@RequestParam("dataSetId") Long dataSetId) {
		return activityService.getNotesHistory(dataSetId);
	}
}
