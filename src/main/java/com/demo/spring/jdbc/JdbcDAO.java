package com.demo.spring.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.demo.model.dto.ReportDTO;

@Repository
public class JdbcDAO {

	@Resource
	private JdbcTemplate jdbcTemplate;
	
	@Resource
	private NamedParameterJdbcTemplate namedParamJdbcTemplate;
	
	public int getCount() {
		final String sql = "SELECT count(*) FROM TEST1";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}
	
	public String getNextId() {
		final String sql = "WITH T as ( SELECT max(to_number(report_id)) as id FROM TEST1 ) "
						+ "SELECT to_char(decode(T.id, null, 1, T.id + 1)) as rptId from T";
		return jdbcTemplate.queryForObject(sql, String.class);
	}
	
	public int insert(Object... params) {
		final String sql = "INSERT INTO TEST1(REPORT_ID, REPORT_NAME, CREATED_BY, CREATION_TIME) "
									+ "values(?, ?, ?, TO_DATE(?,'yyyy-MM-dd'))";
		return jdbcTemplate.update(sql, params);
	}
	
	//PreparedStatementSetter
	public int insertWithPreparedStatementSetter(final Map<String, String> params) {
		final String sql = "INSERT INTO TEST1(REPORT_ID, REPORT_NAME, CREATED_BY, CREATION_TIME) "
									+ "values(?, ?, ?, TO_DATE(?,'yyyy-MM-dd'))";
		return jdbcTemplate.update(sql, new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, getNextId());
				ps.setString(2, params.get("rptName"));
				ps.setString(3, params.get("createdBy"));
				ps.setString(4, params.get("dateStr"));
			}
		});
	}
	
	//
	public int insertWithNamedParameterTemplate(final Map<String, String> params) {
		final String sql = "INSERT INTO TEST1(REPORT_ID, REPORT_NAME, CREATED_BY, CREATION_TIME) "
				+ "values(:rptId, :rptName, :createdBy, TO_DATE(:dateStr,'yyyy-MM-dd'))";
		MapSqlParameterSource sps = new MapSqlParameterSource(params);
		return namedParamJdbcTemplate.update(sql, sps);
	}
	
	//PreparedStatementCreator
	public int update(final Map<String, String> params) {
		final String sql = "UPDATE TEST1 SET REPORT_NAME=?, CREATED_BY=?, "
				+ "CREATION_TIME = TO_DATE(?,'yyyy-MM-dd') WHERE REPORT_ID=?";
		return jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, params.get("rptName"));
				ps.setString(2, params.get("createdBy"));
				ps.setString(3, params.get("dateStr"));
				ps.setString(4, params.get("rptId"));
				return ps;
			}
		});
	}

	//RowMapper: bad for big data
	public ReportDTO selectOne(final String id) {
		final String sql = "select * from TEST1 where REPORT_ID=?";
		return jdbcTemplate.queryForObject(sql, new Object[]{id}, new RowMapper<ReportDTO>(){
			@Override
			public ReportDTO mapRow(ResultSet rs, int index) throws SQLException {
				ReportDTO report = new ReportDTO();
				report.setRptId(rs.getString("REPORT_ID"));
				report.setRptName(rs.getString("REPORT_NAME"));
				report.setCreatedBy(rs.getString("CREATED_BY"));
				report.setDateStr(rs.getString("CREATION_TIME"));
				return report;
			}
		});
	}
	
	public List<Map<String, Object>> getList() {
		final String sql = "select * from message";
		return jdbcTemplate.queryForList(sql);
	}
	
	//RowCallbackHandler: good for big data
	public List<ReportDTO> getListWithRowCallbackHandler(int min) {
		final String sql = "select * from TEST1 where to_number(REPORT_ID)>?";
		final List<ReportDTO> resultList = new ArrayList<ReportDTO>();
		jdbcTemplate.query(sql, new Object[]{min}, new RowCallbackHandler(){
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				ReportDTO report = new ReportDTO();
				report.setRptId(rs.getString("REPORT_ID"));
				report.setRptName(rs.getString("REPORT_NAME"));
				report.setCreatedBy(rs.getString("CREATED_BY"));
				report.setDateStr(rs.getString("CREATION_TIME"));
				resultList.add(report);
			}
		});
		return resultList;
	}
	
	public List<ReportDTO> getListWithRowMapper(int max) {
		final String sql = "select * from TEST1 where to_number(REPORT_ID)<?";
		return jdbcTemplate.query(sql, new Object[]{max}, new RowMapper<ReportDTO>(){
			@Override
			public ReportDTO mapRow(ResultSet rs, int index) throws SQLException {
				ReportDTO report = new ReportDTO();
				report.setRptId(rs.getString("REPORT_ID"));
				report.setRptName(rs.getString("REPORT_NAME"));
				report.setCreatedBy(rs.getString("CREATED_BY"));
				report.setDateStr(rs.getString("CREATION_TIME"));
				return report;
			}
		});
	}
	
}
