package com.test.spring.orm;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.demo.model.Department;
import com.demo.model.Employee;
import com.demo.spring.orm.EmployeeDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/orm/spring-orm-config.xml"})
public class EmployeeDAOTest {
	
	@Resource(name="employeeDAO")
	private EmployeeDAO employeeDao;
	
	@Test
	public void testGetEmployeeList() {
		List<Employee> list = employeeDao.getEmployeeList();
		assertNotNull(list);
		assertTrue("employee size is 0", list.size() > 0);
		System.out.println(list.size());
	}
	
	@Test
	public void testAddEmployee() {
		Employee emp = new Employee("newEmp", "099");
		emp.setSalary(new BigDecimal(1000));
		
		Department dpt = new Department();
		dpt.setId(3);
		emp.setDepartment(dpt);
		employeeDao.addEmployee(emp);
	}
	
	@Test
	public void testUpdateEmployee() {
		
	}
	
	@Test
	public void testDeleteEmployee() {
		
	}
	
	@Test
	public void testJoinClub() {
		employeeDao.joinClub(6,5);
	}
	
	
}
