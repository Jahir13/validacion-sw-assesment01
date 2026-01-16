package com.viraj.sample.service;

import com.viraj.sample.dto.EmployeeDTO;
import com.viraj.sample.entity.Employee;
import com.viraj.sample.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Test
    void testSaveEmployee() {
        Employee employee = new Employee("John Doe", "Developer");
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        EmployeeDTO employeeDTO = new EmployeeDTO(null, "John Doe", "Developer");

        EmployeeDTO savedEmployee = employeeService.saveEmployee(employeeDTO);

        assertNotNull(savedEmployee);
        assertEquals("John Doe", savedEmployee.getEmployeeName());
        assertEquals("Developer", savedEmployee.getEmployeeDescription());
        verify(employeeRepository, times(1)).save(any(Employee.class));
    }

    @Test
    void testGetEmployeeNotFound() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> employeeService.getEmployee(1L));

        assertEquals("Employee not found with id: 1", exception.getMessage());
        verify(employeeRepository, times(1)).findById(1L);
    }
}
