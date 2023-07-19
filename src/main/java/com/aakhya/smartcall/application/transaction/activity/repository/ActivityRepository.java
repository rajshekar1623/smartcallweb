package com.aakhya.smartcall.application.transaction.activity.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.aakhya.smartcall.application.transaction.activity.entity.Activity;
import com.aakhya.smartcall.application.transaction.activity.entity.ActivityPk;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, ActivityPk> {

	@Query("Select c from Activity c where c.dataSetId = :dataSetId and c.userId = :assignedTo "
			+ " and c.activityStatus in ('PENDING','IN-PROCESS') and c.parentActivity is null")
	Activity findActivityAssigedTo(@Param("dataSetId")Long dataSetId,@Param("assignedTo")String assignedTo);
	
	@Query("Select c from Activity c where c.dataSetId = :dataSetId and c.userId = :assignedTo "
			+ " and c.activityStatus = 'PENDING' and c.parentActivity is not null")
	Activity findPendingActivity(@Param("dataSetId")Long dataSetId,@Param("assignedTo")String assignedTo);
	
	@Query("Select c from Activity c where c.dataSetId = :dataSetId order by c.activityId desc")
	List<Activity> findActivityByDataSetId(@Param("dataSetId")Long dataSetId);
	
	@Query("Select c from Activity c where c.dataSetId = :dataSetId and c.parentActivity is null order by c.activityId desc")
	Activity findParentActivityByDataSetId(@Param("dataSetId")Long dataSetId);
	
	/**
	 * 
	 */
	
	@Query("select c from Activity c where c.activityStatus = :activityStatus "
			+ "and dataSetId = :dataSetId and parentActivity is null")
	public Activity findActivityByStatus(@Param("activityStatus") String activityStatus,
			@Param("dataSetId") Long dataSetId);
	@Query("select c from Activity c left outer join c.activityDetails d where c.activityStatus = :activityStatus "
			+ "and dataSetId = :dataSetId and activityType = 1004")
	public Activity findPendingActivity(@Param("activityStatus") String activityStatus,
			@Param("dataSetId") Long dataSetId);
	@Query("select c from Activity c join c.activityDetails d where c.activityType = :activityType "
			+ "and d.scheduleDateTime >= :fromDate "
			+ "and d.scheduleDateTime <= :toDate and c.userId = :assingedTo")
	public List<Activity> findScheduledVisits(
			@Param("activityType") Long activityType,
			@Param("fromDate") Date fromDate,
			@Param("toDate") Date toDate,
			@Param("assingedTo") String assingedTo);
	@Query("select c from Activity c join c.activityDetails d where c.activityType = :activityType "
			+ " and d.scheduleDateTime >= :scheduleDateTime  and c.userId = :assingedTo")
	public List<Activity> findScheduledVisits(
			@Param("activityType") Long activityType,
			@Param("scheduleDateTime") Date scheduleDateTime,
			@Param("assingedTo") String assingedTo);
	@Query("select c from Activity c join c.activityDetails d where c.dataSetId = :dataSetId "
			+ " and c.activityType not in (1001) and c.activityStatus <> 'PENDING' and d.attemptFlow is not null "
			+ " order by c.activityDateTime desc")
	public List<Activity> findActivitesForDataSetId(
			@Param("dataSetId") Long dataSetId);
	@Query("select c from Activity c join c.activityDetails d where c.dataSetId = :dataSetId "
			+ " and d.scheduleDateTime between :fromDate and :toDate and c.activityType not in (1001,1004)"
			+ " and c.activityStatus <> 'PENDING' order by c.activityDateTime desc")
	public List<Activity> findActivitesForDataSetId(
			@Param("dataSetId") Long dataSetId, 
			@Param("fromDate") Date fromDate,
			@Param("toDate") Date toDate);
}
