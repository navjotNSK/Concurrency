package com.navjot.Concurrency.dto;

import com.navjot.Concurrency.entity.Salary;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeDto {
    private int empId;

    private String name;

}
