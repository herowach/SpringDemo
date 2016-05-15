package com.demo.spring.jdbc;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class HiveDAO {

	@Resource
	private JdbcTemplate jdbcTemplate;
	
	public List<Map<String,Object>> getList() {
		final String sql = "select * from test1.dsmt_goc";
		return jdbcTemplate.queryForList(sql);
	}
	
	
	
}
