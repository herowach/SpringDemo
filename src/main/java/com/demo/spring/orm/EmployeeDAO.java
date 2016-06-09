package com.demo.spring.orm;

import java.util.List;

import com.demo.model.Club;
import com.demo.model.Department;
import com.demo.model.Employee;

public interface EmployeeDAO {
	void addEmployee(Employee emp);
	
	void updateEmployee(Employee emp);
	
	void deleteEmployee(int id);
	
	Employee getEmployeeById(int id);
	
	List<Employee> getEmployeeList();
	
	List<Employee> getEmployeeList(Department dpt);
	
	List<Employee> getEmployeeList(Club club);
	
	void joinClub(int empId, int clubId);
	
	void quitClub(int empId, int clubId);
}
