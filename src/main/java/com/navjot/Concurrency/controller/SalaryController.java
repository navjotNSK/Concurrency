package com.navjot.Concurrency.controller;


import com.navjot.Concurrency.dto.EmployeeDto;
import com.navjot.Concurrency.dto.SalaryDto;
import com.navjot.Concurrency.service.SalaryService;
import com.navjot.Concurrency.transformer.SalaryDtoToSalaryEntityTransformer;
import com.navjot.Concurrency.util.Audit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/salary")
public class SalaryController {

    @Autowired
    private SalaryService salaryService;

    @Autowired
    private SalaryDtoToSalaryEntityTransformer salaryDtoToSalaryEntityTransformer;

    @PostMapping("")
    @Audit(description = "Post API to save employee details")
    public ResponseEntity<SalaryDto> processOrder(@RequestBody SalaryDto salaryDto) throws InterruptedException {
        salaryService.saveSalary(salaryDtoToSalaryEntityTransformer.apply(salaryDto));
        return ResponseEntity.ok(salaryDto);
    }

}
