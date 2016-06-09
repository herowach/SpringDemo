package com.demo.spring.orm.mybatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

import com.demo.model.Club;
import com.demo.model.Department;
import com.demo.model.Employee;
import com.demo.spring.orm.EmployeeDAO;

@Repository("employeeDAO")
public class EmployeeDAOImpl implements EmployeeDAO {

	@Resource
	private SqlSessionFactory sqlSessionFactory;
	
	@Override
	public void addEmployee(Employee emp) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		sqlSession.insert("addEmployee", emp);
	}

	@Override
	public void updateEmployee(Employee emp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteEmployee(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Employee getEmployeeById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> getEmployeeList() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		List<Employee> list = sqlSession.selectList("getEmployeeList");
		return list;
	}

	@Override
	public List<Employee> getEmployeeList(Department dpt) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> getEmployeeList(Club club) {
		
		return null;
	}
	
	@Override
	public void joinClub(int empId, int clubId) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		Map<String, Integer> param = new HashMap<>();
		param.put("employeeId", empId);
		param.put("clubId", clubId);
		sqlSession.insert("joinClub", param);
	}

	@Override
	public void quitClub(int empId, int clubId) {
		
	}

}
