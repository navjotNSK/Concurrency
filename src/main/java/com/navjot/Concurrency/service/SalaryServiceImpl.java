package com.navjot.Concurrency.service;

import com.navjot.Concurrency.entity.Employee;
import com.navjot.Concurrency.entity.Salary;
import com.navjot.Concurrency.exception.ProductIdNotFoundException;
import com.navjot.Concurrency.repository.EmployeeRepository;
import com.navjot.Concurrency.repository.SalaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalaryServiceImpl implements SalaryService{
    @Autowired
    private SalaryRepository salaryRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public Salary saveSalary(Salary salary) {
        Optional<Employee> employee = employeeRepository.findById(salary.getEmployee().getEmpId());
        if (!employee.isPresent()) {
            throw new ProductIdNotFoundException("Employee not found with ID: " + salary.getEmployee().getEmpId());
        }

        salary.setEmployee(employee.get());

        return salaryRepository.save(salary);
    }

    public List<Salary> getAllSalaries() {
        return salaryRepository.findAll();
    }
}
