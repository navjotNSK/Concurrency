package com.navjot.Concurrency.controller;

import com.navjot.Concurrency.dto.EmployeeDto;
import com.navjot.Concurrency.dto.Inventory;
import com.navjot.Concurrency.service.EmployeeService;
import com.navjot.Concurrency.transformer.EmployeeDtoToEmployeeEntityTransformer;
import com.navjot.Concurrency.util.Audit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeDtoToEmployeeEntityTransformer employeeDtoToEmployeeEntityTransformer;

    @PostMapping()
    @Audit(description = "Post API to save employee details")
    public ResponseEntity<EmployeeDto> processOrder(@RequestBody EmployeeDto employeeDto) throws InterruptedException {
        employeeService.saveEmployee(employeeDtoToEmployeeEntityTransformer.apply(employeeDto));
        return ResponseEntity.ok(employeeDto);
    }


}
