package com.navjot.Concurrency.transformer;

import com.navjot.Concurrency.dto.SalaryDto;
import com.navjot.Concurrency.entity.Employee;
import com.navjot.Concurrency.entity.Salary;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SalaryDtoToSalaryEntityTransformer {

    @Autowired
    private ModelMapper mapper;

    public Salary apply(SalaryDto salaryDto){
        Salary salary =  mapper.map(salaryDto, Salary.class);
        salary.setEmployee(Employee.builder().empId(salaryDto.getEmpId()).build());
        return salary;
    }
}
