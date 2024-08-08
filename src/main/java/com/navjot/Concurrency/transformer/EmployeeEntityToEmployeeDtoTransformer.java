package com.navjot.Concurrency.transformer;

import com.navjot.Concurrency.dto.EmployeeDto;
import com.navjot.Concurrency.entity.Employee;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeEntityToEmployeeDtoTransformer {
    @Autowired
    private ModelMapper mapper;

    public EmployeeDto apply(Employee employee){
        return mapper.map(employee, EmployeeDto.class);
    }
}
