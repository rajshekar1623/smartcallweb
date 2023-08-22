package com.aakhya.smartcall.application.dashboard.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.aakhya.smartcall.application.admin.entity.Branch;
import com.aakhya.smartcall.application.dashboard.entity.ActivitySummary;
import com.aakhya.smartcall.application.dashboard.entity.ActivitySummaryUser;
import com.aakhya.smartcall.application.dashboard.entity.CashCollection;
import com.aakhya.smartcall.application.dashboard.entity.FieldVisitByUser;

@Repository
public class DashboardJdbcRepocitory {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<ActivitySummary> getActivitySummaryBranchWise(Date fromDate, Date toDate, Branch cluster,
			Branch branch) {
		List<ActivitySummary> summaries = new ArrayList<ActivitySummary>();
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer query = new StringBuffer();
		query.append(" select dbo.fn_getBranchName(assgn.branchCode,1) branchName, ");
		query.append(" assignCount,case when calledOnes is null then 0 else calledOnes end calledOnes , ");
		query.append(" case when calledTwice is null then 0 else calledTwice end calledTwice, ");
		query.append(" case when calledThrice is null then 0 else calledThrice end calledThrice, ");
		query.append(
				" case when calledMoneThanthrice is null then 0 else calledMoneThanthrice end calledMoneThanthrice from  ");
		query.append(" (select branchCode,count(1) assignCount ");
		query.append(" from sc_activity a where a.activityDescription = 'ASSIGN' ");
		if (null != fromDate && null != toDate) {
			query.append(" and convert(varchar,a.activityDateTime,105) >= convert(varchar,:fromDate,105) ");
			query.append(" and convert(varchar,a.activityDateTime,105) <= convert(varchar,:toDate,105) ");
			params.put("fromDate", fromDate);
			params.put("toDate", toDate);
		}
		if (null != cluster) {
			query.append(" and a.branchCode in (select branchCode from sc_branch where parentBranch = :cluster) ");
			params.put("cluster", cluster.getBranchCode());
		}
		if (null != branch) {
			query.append(" and a.branchCode =:branch");
			params.put("branch", branch.getBranchCode());
		}
		query.append(" group by branchCode ) assgn ");
		query.append(" left outer join  ");
		query.append(" (select branchCode,count(dataSetId) calledOnes ");
		query.append(" from sc_activity a where a.activityDescription = 'CALL' ");
		if (null != fromDate && null != toDate) {
			query.append(" and convert(varchar,a.activityDateTime,105) >= convert(varchar,:fromDate,105) ");
			query.append(" and convert(varchar,a.activityDateTime,105) <= convert(varchar,:toDate,105) ");
			params.put("fromDate", fromDate);
			params.put("toDate", toDate);
		}
		if (null != cluster) {
			query.append(" and a.branchCode in (select branchCode from sc_branch where parentBranch = :cluster) ");
			params.put("cluster", cluster.getBranchCode());
		}
		if (null != branch) {
			query.append(" and a.branchCode =:branch");
			params.put("branch", branch.getBranchCode());
		}
		query.append(" group by branchCode having count(dataSetId) = 1) cone ");
		query.append(" on(assgn.branchCode = cone.branchCode) ");
		query.append(" left outer join  ");
		query.append(" (select branchCode,count(dataSetId) calledTwice ");
		query.append(" from sc_activity a where a.activityDescription = 'CALL' ");
		if (null != fromDate && null != toDate) {
			query.append(" and convert(varchar,a.activityDateTime,105) >= convert(varchar,:fromDate,105) ");
			query.append(" and convert(varchar,a.activityDateTime,105) <= convert(varchar,:toDate,105) ");
			params.put("fromDate", fromDate);
			params.put("toDate", toDate);
		}
		if (null != cluster) {
			query.append(" and a.branchCode in (select branchCode from sc_branch where parentBranch = :cluster) ");
			params.put("cluster", cluster.getBranchCode());
		}
		if (null != branch) {
			query.append(" and a.branchCode =:branch");
			params.put("branch", branch.getBranchCode());
		}
		query.append(" group by branchCode having count(1) = 2 ) ctwice ");
		query.append(" on(assgn.branchCode = ctwice.branchCode) ");
		query.append(" left outer join  ");
		query.append(" (select branchCode,count(dataSetId) calledThrice ");
		query.append(" from sc_activity a where a.activityDescription = 'CALL' ");
		if (null != fromDate && null != toDate) {
			query.append(" and convert(varchar,a.activityDateTime,105) >= convert(varchar,:fromDate,105) ");
			query.append(" and convert(varchar,a.activityDateTime,105) <= convert(varchar,:toDate,105) ");
			params.put("fromDate", fromDate);
			params.put("toDate", toDate);
		}
		if (null != cluster) {
			query.append(" and a.branchCode in (select branchCode from sc_branch where parentBranch = :cluster) ");
			params.put("cluster", cluster.getBranchCode());
		}
		if (null != branch) {
			query.append(" and a.branchCode =:branch");
			params.put("branch", branch.getBranchCode());
		}
		query.append(" group by branchCode having count(dataSetId) = 3) cthrice ");
		query.append(" on(assgn.branchCode = cthrice.branchCode) ");
		query.append(" left outer join  ");
		query.append(" (select branchCode,count(dataSetId) calledMoneThanthrice ");
		query.append(" from sc_activity a where a.activityDescription = 'CALL' ");
		if (null != fromDate && null != toDate) {
			query.append(" and convert(varchar,a.activityDateTime,105) >= convert(varchar,:fromDate,105) ");
			query.append(" and convert(varchar,a.activityDateTime,105) <= convert(varchar,:toDate,105) ");
			params.put("fromDate", fromDate);
			params.put("toDate", toDate);
		}
		if (null != cluster) {
			query.append(" and a.branchCode in (select branchCode from sc_branch where parentBranch = :cluster) ");
			params.put("cluster", cluster.getBranchCode());
		}
		if (null != branch) {
			query.append(" and a.branchCode =:branch");
			params.put("branch", branch.getBranchCode());
		}query.append(" group by branchCode having count(dataSetId) >3) cMth ");
		query.append(" on(assgn.branchCode = cMth.branchCode) ");
		query.append(" order by  dbo.fn_getBranchName(assgn.branchCode,1) ");
		summaries = namedParameterJdbcTemplate.query(query.toString(),params, new RowMapper() {

			@Override
			public ActivitySummary mapRow(ResultSet rs, int rowNum) throws SQLException {
				ActivitySummary activitySummary = new ActivitySummary();
				activitySummary.setBranchName(rs.getString("branchName"));
				activitySummary.setNoOfAccountsAssigned(rs.getInt("assignCount"));
				activitySummary.setNoOfAcsCalledOnes(rs.getInt("calledOnes"));
				activitySummary.setNoOfAcsCalledTwice(rs.getInt("calledTwice"));
				activitySummary.setNoOfAcsCalledThrice(rs.getInt("calledThrice"));
				activitySummary.setNoOfAcsCalledMoreThanThrice(rs.getInt("calledMoneThanthrice"));
				return activitySummary;
			}
		});
		return summaries;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<ActivitySummaryUser> getActivitySummaryUserwise(Date fromDate, Date toDate, Branch cluster,
			Branch branch) {
		List<ActivitySummaryUser> summaries = new ArrayList<ActivitySummaryUser>();
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer query = new StringBuffer();
		query.append(" select distinct dbo.fn_getUserName(assgn.userId,1) userName,dbo.fn_getBranchName(assgn.branchCode,1) branchName, ");
		query.append(" assignCount,dbo.fn_getCallsCount(assgn.userId,assgn.branchCode,1) calledOnes , ");
		query.append(" dbo.fn_getCallsCount(assgn.userId,assgn.branchCode,2) calledTwice, ");
		query.append(" dbo.fn_getCallsCount(assgn.userId,assgn.branchCode,3) calledThrice, ");
		query.append(" dbo.fn_getCallsCount(assgn.userId,assgn.branchCode,4) calledMoneThanthrice from ");
		query.append(" (select userId,branchCode,count(1) assignCount ");
		query.append(" from sc_activity a where a.activityDescription = 'ASSIGN' ");
		if (null != fromDate && null != toDate) {
			query.append(" and convert(varchar,a.activityDateTime,105) >= convert(varchar,:fromDate,105) ");
			query.append(" and convert(varchar,a.activityDateTime,105) <= convert(varchar,:toDate,105) ");
			params.put("fromDate", fromDate);
			params.put("toDate", toDate);
		}
		query.append(" group by userId,branchCode ) assgn  where 1 = 1 ");
		
		if (null != cluster) {
			query.append(" and assgn.branchCode in (select branchCode from sc_branch where parentBranch = :cluster) ");
			params.put("cluster", cluster.getBranchCode());
		}
		if (null != branch) {
			query.append(" and assgn.branchCode =:branch");
			params.put("branch", branch.getBranchCode());
		}
		summaries = namedParameterJdbcTemplate.query(query.toString(),params, new RowMapper() {

			@Override
			public ActivitySummaryUser mapRow(ResultSet rs, int rowNum) throws SQLException {
				ActivitySummaryUser activitySummary = new ActivitySummaryUser();
				activitySummary.setBranchName(rs.getString("branchName"));
				activitySummary.setUserName(rs.getString("userName"));
				activitySummary.setNoOfAccountsAssigned(rs.getInt("assignCount"));
				activitySummary.setNoOfAcsCalledOnes(rs.getInt("calledOnes"));
				activitySummary.setNoOfAcsCalledTwice(rs.getInt("calledTwice"));
				activitySummary.setNoOfAcsCalledThrice(rs.getInt("calledThrice"));
				activitySummary.setNoOfAcsCalledMoreThanThrice(rs.getInt("calledMoneThanthrice"));
				Integer assigned = 0;
				Integer once = 0;
				Integer twice = 0;
				Integer thrice = 0;
				Integer moreThanThrice = 0;
				if(null != activitySummary.getNoOfAccountsAssigned())
					assigned = activitySummary.getNoOfAccountsAssigned();
				if(null != activitySummary.getNoOfAcsCalledOnes())
					once = activitySummary.getNoOfAcsCalledOnes();
				if(null != activitySummary.getNoOfAcsCalledTwice())
					twice = activitySummary.getNoOfAcsCalledTwice();
				if(null != activitySummary.getNoOfAcsCalledThrice())
					thrice = activitySummary.getNoOfAcsCalledThrice();
				if(null != activitySummary.getNoOfAcsCalledMoreThanThrice())
					moreThanThrice = activitySummary.getNoOfAcsCalledMoreThanThrice();
				Integer notCalled = assigned - (once+twice+thrice+moreThanThrice);
				activitySummary.setNotCalled(notCalled);
				return activitySummary;
			}
		});
		return summaries;
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<ActivitySummary> getActivitySummaryByCluster(){
		List<ActivitySummary> summaries = new ArrayList<>();
		StringBuffer query = new StringBuffer();
		query.append(" select 'Guntur' clusterName,sum(assignCount) assigned,sum(calledOnes) once,sum(calledTwice) twice,sum(calledThrice) thrice, ");
		query.append(" 	sum(calledMoneThanthrice) morethanThrice from ( ");
		query.append(" 	select distinct dbo.fn_getUserName(assgn.userId,1) userName,dbo.fn_getBranchName(assgn.branchCode,1) branchName,  ");
		query.append(" 	assignCount,dbo.fn_getCallsCount(assgn.userId,assgn.branchCode,1) calledOnes ,  ");
		query.append(" dbo.fn_getCallsCount(assgn.userId,assgn.branchCode,2) calledTwice,  ");
		query.append(" 	dbo.fn_getCallsCount(assgn.userId,assgn.branchCode,3) calledThrice,  ");
		query.append(" 	dbo.fn_getCallsCount(assgn.userId,assgn.branchCode,4) calledMoneThanthrice from   ");
		query.append(" 		 (select userId,branchCode,count(1) assignCount  ");
		query.append(" 		 from sc_activity a where a.activityDescription = 'ASSIGN'  ");
		query.append(" 		 group by userId,branchCode ) assgn where 1 = 1  ");
		query.append(" 		 and assgn.branchCode in (select branchCode from sc_branch where parentBranch = 'GU') ) A ");
		query.append(" 		 union ");
		query.append(" 		 select 'Krishna' clusterName,sum(assignCount),sum(calledOnes),sum(calledTwice),sum(calledThrice),sum(calledMoneThanthrice) from ( ");
		query.append(" 		 select distinct dbo.fn_getUserName(assgn.userId,1) userName,dbo.fn_getBranchName(assgn.branchCode,1) branchName,  ");
		query.append(" 		 assignCount,dbo.fn_getCallsCount(assgn.userId,assgn.branchCode,1) calledOnes ,  ");
		query.append(" 		dbo.fn_getCallsCount(assgn.userId,assgn.branchCode,2) calledTwice,  ");
		query.append(" 		 dbo.fn_getCallsCount(assgn.userId,assgn.branchCode,3) calledThrice,  ");
		query.append(" 		 dbo.fn_getCallsCount(assgn.userId,assgn.branchCode,4) calledMoneThanthrice from   ");
		query.append(" 		 (select userId,branchCode,count(1) assignCount  ");
		query.append(" 		 from sc_activity a where a.activityDescription = 'ASSIGN'  ");
		query.append(" 		 group by userId,branchCode ) assgn where 1 = 1  ");
		query.append(" 		 and assgn.branchCode in (select branchCode from sc_branch where parentBranch = 'KR') ) A ");
		query.append(" 		 union ");
		query.append(" 		 select 'Vishakapatinum' clusterName,sum(assignCount),sum(calledOnes),sum(calledTwice),sum(calledThrice),sum(calledMoneThanthrice) from ( ");
		query.append(" 		 select distinct dbo.fn_getUserName(assgn.userId,1) userName,dbo.fn_getBranchName(assgn.branchCode,1) branchName,  ");
		query.append(" 		 assignCount,dbo.fn_getCallsCount(assgn.userId,assgn.branchCode,1) calledOnes ,  ");
		query.append(" 		dbo.fn_getCallsCount(assgn.userId,assgn.branchCode,2) calledTwice,  ");
		query.append(" 		 dbo.fn_getCallsCount(assgn.userId,assgn.branchCode,3) calledThrice,  ");
		query.append(" 		 dbo.fn_getCallsCount(assgn.userId,assgn.branchCode,4) calledMoneThanthrice from   ");
		query.append(" 		 (select userId,branchCode,count(1) assignCount  ");
		query.append(" 		 from sc_activity a where a.activityDescription = 'ASSIGN'  ");
		query.append(" 		 group by userId,branchCode ) assgn where 1 = 1  ");
		query.append(" 		 and assgn.branchCode in (select branchCode from sc_branch where parentBranch = 'VS') ) A ");
		summaries = namedParameterJdbcTemplate.query(query.toString(),  new RowMapper() {

			@Override
			public ActivitySummary mapRow(ResultSet rs, int rowNum) throws SQLException {
				ActivitySummary activitySummary = new ActivitySummary();
				activitySummary.setBranchName(rs.getString("clusterName"));
				activitySummary.setNoOfAccountsAssigned(rs.getInt("assigned"));
				activitySummary.setNoOfAcsCalledOnes(rs.getInt("once"));
				activitySummary.setNoOfAcsCalledTwice(rs.getInt("twice"));
				activitySummary.setNoOfAcsCalledThrice(rs.getInt("thrice"));
				activitySummary.setNoOfAcsCalledMoreThanThrice(rs.getInt("morethanThrice"));
				return activitySummary;
			}
		});
		return summaries;
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<CashCollection> findCashCollections(Date fromDate, Date toDate, Branch cluster,
			Branch branch){
		List<CashCollection> cashCollections = new ArrayList<CashCollection>();
		StringBuffer query = new StringBuffer();
		query.append(" select sc.generic1 employeeId,sc.userName,firstName,trn.genericNumber2 accountNumber,dbo.fn_getBranchName(trn.branchCode,1) branchName, ");
		query.append(" act.activityDateTime,act.genericDecimal1 amountCollected ");
		query.append("  from sc_transactionDataSet trn join sc_activity act ");
		query.append(" on(trn.dataSetId = act.dataSetId and act.genericDecimal1 is not null) ");
		query.append(" join sc_user sc on(act.userId = sc.userId) where 1= 1");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		if(null != fromDate && null != toDate) {
			query.append(" and act.activityDateTime >= :fromDate and act.activityDateTime <= :toDate ");
			paramMap.put("fromDate", fromDate);
			paramMap.put("toDate", toDate);
		}
		if(null != cluster) {
			query.append(" and trn.branchCode in (select branchCode from sc_branch where parentBranch = :cluster) ");
			paramMap.put("cluster", cluster.getBranchCode());
		}
		if(null != branch) {
			query.append(" and trn.branchCode = :branch ");
			paramMap.put("branch", branch.getBranchCode());
		}
		cashCollections = namedParameterJdbcTemplate.query(query.toString(), paramMap,new RowMapper() {

			@Override
			public CashCollection mapRow(ResultSet rs, int rowNum) throws SQLException {
				CashCollection cashCollection = new CashCollection();
				cashCollection.setAccountNumber(rs.getString("accountNumber"));
				cashCollection.setAmountCollected(rs.getString("amountCollected"));
				cashCollection.setBranchName(rs.getString("branchName"));
				cashCollection.setCollectionDate(rs.getString("activityDateTime"));
				cashCollection.setCustomerName(rs.getString("firstName"));
				cashCollection.setEmployeeId(rs.getString("employeeId"));
				cashCollection.setUserName(rs.getString("userName"));
				return cashCollection;
			}
		});
		return cashCollections;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<FieldVisitByUser> findFieldVisitsByUser(){
		List<FieldVisitByUser> visitisByUser = new ArrayList<FieldVisitByUser>();
		StringBuffer query = new StringBuffer();
		query.append(" select act.userId,dbo.fn_getUserName(userId,trn.companyId) userName, ");
		query.append(" trn.genericNumber2 accountNumber,trn.firstName customerName, "); 
		query.append(" brn.genericDecimal1 brnLat,brn.genericDecimal2 brnLon, "); 
		query.append(" convert(varchar,actDet.scheduleDateTime,106) meetingDate, ");
		query.append(" act.genericDecimal1 meetLat,act.genericDecimal2 meetLon, "); 
		query.append(" trn.genericDecimal10 custLat,trn.genericDecimal11 custLon, ");
		query.append(" trn.genericDecimal12 distFromBrn,null varianceKm ");
		query.append(" from sc_transactionDataSet trn join sc_activity act ");
		query.append(" on(trn.dataSetId = act.dataSetId and act.activityType = 1004 ");
		query.append(" and act.activityStatus = 'COMPLETE') join sc_activityDetail actDet ");
		query.append(" on(act.activityId = actDet.activityId and actDet.scheduleType = 'VISIT') ");
		query.append(" join sc_branch brn on(trn.branchCode = brn.branchCode) order by act.userId");
		visitisByUser = namedParameterJdbcTemplate.query(query.toString(), new RowMapper() {

			@Override
			public FieldVisitByUser mapRow(ResultSet rs, int rowNum) throws SQLException {
				FieldVisitByUser fieldVisitByUser = new FieldVisitByUser();
				fieldVisitByUser.setUserName(rs.getString("userName"));
				fieldVisitByUser.setAccountNumber(rs.getString("accountNumber"));
				fieldVisitByUser.setCustomerName(rs.getString("customerName"));
				fieldVisitByUser.setBranchLat(rs.getDouble("brnLat"));
				fieldVisitByUser.setBranchLon(rs.getDouble("brnLon"));
				fieldVisitByUser.setMeetingDate(rs.getString("meetingDate"));
				fieldVisitByUser.setMeetingLat(rs.getDouble("meetLat"));
				fieldVisitByUser.setMeetingLon(rs.getDouble("meetLon"));
				fieldVisitByUser.setCustLat(rs.getDouble("custLat"));
				fieldVisitByUser.setCustLon(rs.getDouble("custLon"));
				fieldVisitByUser.setDistanceFromBranch(rs.getDouble("distFromBrn"));
				fieldVisitByUser.setVariance(rs.getDouble("varianceKm"));
				return fieldVisitByUser;
			}
		});
		return visitisByUser;
	}
}