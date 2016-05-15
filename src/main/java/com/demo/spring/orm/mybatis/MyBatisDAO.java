package com.demo.spring.orm.mybatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

import com.demo.model.dto.ReportDTO;

@Repository("mybatisDAO")
public class MyBatisDAO {
	
	@Resource
	private SqlSessionFactory sqlSessionFactory;
	
	@Resource
	private SqlSession sqlSession;
	
	public List<ReportDTO> getDataWithSqlSessionFactory() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
        List<ReportDTO> result = sqlSession.selectList("getTest");
		return result;
	}
	
	public List<ReportDTO> getDataWithSqlSessionTemplate() {
		List<ReportDTO> result = sqlSession.selectList("getTest");
		return result;
	}
	
	public List<ReportDTO> getListWithPagination(int offset, int pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("createdBy", "cw67094");
		List<ReportDTO> result = sqlSession.selectList("getTestWithParam", map, new RowBounds(offset, pageSize));
		return result;
	}
	
	public String getReportName(String rptId) {
		String result = sqlSession.selectOne("selectOne", rptId);
		return result;
	}
	
	public void insertTest(Map<String, String> params) {
		sqlSession.insert("insertTest", params);
	}
	
	public void updateTest(Map<String, String> params) {
		sqlSession.update("updateTest", params);
	}
	
}
