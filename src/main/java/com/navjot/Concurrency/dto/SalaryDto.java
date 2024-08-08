package com.navjot.Concurrency.dto;

import com.navjot.Concurrency.entity.Employee;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SalaryDto {
    private UUID id;

    private double sal;

    private String mon;

    private Integer empId;
}
