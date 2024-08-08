package com.navjot.Concurrency.service;

import com.navjot.Concurrency.entity.Employee;
import java.util.List;

public interface EmployeeService {
     Employee saveEmployee(Employee employee);
     List<Employee> getAllEmployees() ;
}
