package com.navjot.Concurrency.transformer;

import com.navjot.Concurrency.dto.EmployeeDto;
import com.navjot.Concurrency.dto.Inventory;
import com.navjot.Concurrency.entity.Employee;
import com.navjot.Concurrency.entity.InventoryEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeDtoToEmployeeEntityTransformer {

    @Autowired
    private ModelMapper mapper;

    public Employee apply(EmployeeDto employeeDto){
        return mapper.map(employeeDto, Employee.class);
    }


}
