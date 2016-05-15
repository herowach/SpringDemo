package com.demo.spring.ioc;

import java.math.BigDecimal;

import org.springframework.beans.factory.FactoryBean;

import com.demo.model.Employee;

//Spring treats FactoryBean as a proxy of the target class, we can inject this proxy on behalf of the target class 
public class EmployeeFactoryBean implements FactoryBean<Employee> {

	private Employee employee;
	
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Employee getObject() throws Exception {
		//do some convert and handler on the result
		if(employee == null) {
			employee = new Employee();
			employee.setId("-1");
			employee.setName("unknown");
			employee.setCode("-99");
			employee.setSalary(new BigDecimal(0));
		}
		
		Employee target = new Employee();
		target.setId(employee.getId());
		target.setName(employee.getName());
		target.setCode(employee.getCode());
		target.setSalary(employee.getSalary());
		return target;
	}

	public Class<?> getObjectType() {
		return Employee.class;
	}

	public boolean isSingleton() {
		return false;
	}

}
