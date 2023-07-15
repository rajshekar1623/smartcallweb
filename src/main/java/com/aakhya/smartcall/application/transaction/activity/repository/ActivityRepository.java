package com.aakhya.smartcall.application.transaction.activity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.aakhya.smartcall.application.transaction.activity.entity.Activity;
import com.aakhya.smartcall.application.transaction.activity.entity.ActivityPk;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, ActivityPk> {

	@Query("Select c from Activity c where c.dataSetId = :dataSetId and c.userId = :assignedTo "
			+ " and c.activityStatus = :activityStatus and c.parentActivity is null")
	Activity findActivityAssigedTo(@Param("dataSetId")Long dataSetId,@Param("assignedTo")String assignedTo,
			@Param("activityStatus") String activityStatus);
}
