package com.demo.spring.orm.mybatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.demo.model.Club;
import com.demo.spring.orm.ClubDAO;

@Repository("clubDAO")
public class ClubDAOImpl implements ClubDAO {

	@Resource
	private SqlSession sqlSession;
	
	@Override
	public void addClub(Club club) {
		sqlSession.insert("addClub", club);
	}

	@Override
	public void updateClub(Club club) {
		sqlSession.update("updateClub", club);
	}

	@Override
	public void deleteClub(int id) {
		//delete membership first
		
		//then delete club
		sqlSession.delete("deleteClub", id);
	}
	
	@Override
	public Club getClubById(int clubId){
		Map<String, Object> param = new HashMap<>();
		param.put("id", clubId);
		return sqlSession.selectOne("getClubList", param);
	}
	
	@Override
	public Club getClubByName(String name){
		Map<String, Object> param = new HashMap<>();
		param.put("name", name);
		return sqlSession.selectOne("getClubList", param);
	}

	@Override
	public List<Club> getClubList(Map<String, Object> param) {
		List<Club> list = sqlSession.selectList("getClubList", param);
		return list;
	}

	@Override
	public List<Club> getClubListByEmployeeId(int empId) {
		return null;
	}

	public void deleteMembershipByClubId(int clubId){
		sqlSession.delete("deleteMembershipByClubId", clubId);
	}
	
}
