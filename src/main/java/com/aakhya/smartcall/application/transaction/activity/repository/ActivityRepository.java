package com.aakhya.smartcall.application.transaction.activity.repository;

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
}
