package com.aakhya.smartcall.application.dashboard.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aakhya.smartcall.application.admin.entity.Branch;
import com.aakhya.smartcall.application.dashboard.entity.ActivitySummary;
import com.aakhya.smartcall.application.dashboard.entity.ActivitySummaryUser;
import com.aakhya.smartcall.application.dashboard.entity.CashCollection;
import com.aakhya.smartcall.application.dashboard.entity.DashBoard;
import com.aakhya.smartcall.application.dashboard.repository.DashboardJdbcRepocitory;
import com.aakhya.smartcall.application.security.entity.User;


@Service
public class DashBoardService {
	
	@Autowired
	private DashboardJdbcRepocitory dashboardJdbcRepocitory;

	public List<DashBoard> getDashBoardForUser(User user){
		List<DashBoard> dashBoardItems = new ArrayList<DashBoard>();
		dashBoardItems.add(new DashBoard("Marketing", 28, 12,1));
		dashBoardItems.add(new DashBoard("Collection", 13, 36,1));
		dashBoardItems.add(new DashBoard("Welcome Call", 43, 12,1));
		dashBoardItems.add(new DashBoard("Renewal", 13, 16,1));
		return dashBoardItems;
	}
	public List<ActivitySummary> getActivitySummaryByCluster(){
		return dashboardJdbcRepocitory.getActivitySummaryByCluster();
	}
	public List<ActivitySummary> getActivitySummaryBranchWise(Date fromDate, Date toDate, Branch cluster,
			Branch branch) {
		return dashboardJdbcRepocitory.getActivitySummaryBranchWise(fromDate, toDate, cluster, branch);
	}
	
	public List<ActivitySummaryUser> getActivitySummaryUserWise(Date fromDate, Date toDate, Branch cluster,
			Branch branch){
		return dashboardJdbcRepocitory.getActivitySummaryUserwise(fromDate, toDate, cluster, branch);
	}
	
	public List<CashCollection> findCashCollections(Date fromDate, Date toDate, Branch cluster,
			Branch branch){
		return dashboardJdbcRepocitory.findCashCollections(fromDate, toDate, cluster, branch);
	}
}
