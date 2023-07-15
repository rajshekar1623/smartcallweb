package com.aakhya.smartcall.application.admin.repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Repository;

@Repository
public class SequenceJDBCDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public Long getSequenceForEntity(final String entityName,final Long companyId){
		List<SqlParameter> declaredParameters = new ArrayList<SqlParameter>();
		declaredParameters.add(new SqlParameter("in_entityName", Types.VARCHAR));
		declaredParameters.add(new SqlParameter("in_companyId", Types.BIGINT));
		declaredParameters.add(new SqlOutParameter("seq", Types.BIGINT));
		Map<String,Object> storedProcResult = jdbcTemplate.call(new CallableStatementCreator() {
			public CallableStatement createCallableStatement(Connection con)
					throws SQLException {
				CallableStatement stmnt = null;
				try {
					stmnt = con.prepareCall("EXEC [dbo].[sp_getSequence] ?,?,?");
					stmnt.setString("in_entityName", entityName);
					stmnt.setLong("in_companyId", companyId);
					stmnt.registerOutParameter("seq", Types.BIGINT);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return stmnt;
			}
		}, declaredParameters);
		Long seq = (Long)storedProcResult.get("seq");
		return seq;
	}
}