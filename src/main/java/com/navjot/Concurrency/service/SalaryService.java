package com.navjot.Concurrency.service;

import com.navjot.Concurrency.entity.Salary;

import java.util.List;

public interface SalaryService {

     Salary saveSalary(Salary salary);

     List<Salary> getAllSalaries() ;
}
