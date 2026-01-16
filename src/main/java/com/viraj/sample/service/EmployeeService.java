package com.viraj.sample.service;

import com.viraj.sample.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeService {

    EmployeeDTO saveEmployee(EmployeeDTO employee);
    EmployeeDTO updateEmployee(EmployeeDTO employee);
    List<EmployeeDTO> getAllEmployees();
    EmployeeDTO getEmployee(Long employeeId);
    void deleteEmployee(Long employeeId);
}
