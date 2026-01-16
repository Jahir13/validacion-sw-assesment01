package com.viraj.sample.service;

import com.viraj.sample.dto.EmployeeDTO;
import com.viraj.sample.entity.Employee;
import com.viraj.sample.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
        Employee employee = toEntity(employeeDTO);
        Employee savedEmployee = employeeRepository.save(employee);
        return toDTO(savedEmployee);
    }

    @Override
    public EmployeeDTO updateEmployee(EmployeeDTO employeeDTO) {
        Employee employee = toEntity(employeeDTO);
        Employee updatedEmployee = employeeRepository.save(employee);
        return toDTO(updatedEmployee);
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> employees = (List<Employee>) employeeRepository.findAll();
        return employees.stream().map(this::toDTO).toList();
    }

    @Override
    public EmployeeDTO getEmployee(Long employeeId) {
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        if (employee.isPresent()) {
            return toDTO(employee.get());
        } else {
            throw new IllegalArgumentException("Employee not found with id: " + employeeId);
        }
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        employeeRepository.deleteById(employeeId);
    }

    private EmployeeDTO toDTO(Employee employee) {
        return new EmployeeDTO(
                employee.getEmployeeId(),
                employee.getEmployeeName(),
                employee.getEmployeeDescription()
        );
    }

    private Employee toEntity(EmployeeDTO dto) {
        Employee employee = new Employee();
        if (dto.getEmployeeId() != null) {
            employee.setEmployeeId(dto.getEmployeeId());
        }
        employee.setEmployeeName(dto.getEmployeeName());
        employee.setEmployeeDescription(dto.getEmployeeDescription());
        return employee;
    }
}
