package com.navjot.Concurrency.transformer;

import com.navjot.Concurrency.dto.SalaryDto;
import com.navjot.Concurrency.entity.Salary;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SalaryEntityToSalaryDtoTransformer {

    @Autowired
    private ModelMapper mapper;

    public SalaryDto apply(Salary Salary){
        return mapper.map(Salary, SalaryDto.class);
    }
}
