package com.demo.spring.orm;

import java.util.List;

import com.demo.model.Department;

public interface DepartmentDAO {
	void addDepartment(Department department);
	
	void updateDepartment(Department department);
	
	void deleteDepartment(int id);
	
	List<Department> getDepartmentList();
}
