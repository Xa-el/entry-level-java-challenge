package com.challenge.api.service;

import com.challenge.api.exception.EmployeeNotFoundException;
import com.challenge.api.exception.InvalidEmployeeException;
import com.challenge.api.model.Employee;
import com.challenge.api.model.IndividualEmployee;
import com.challenge.api.model.IndividualEmployeeRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    // In-memory storage where the key is the employee UUID and the value is the employee model
    private final Map<UUID, Employee> employees = new ConcurrentHashMap<>();

    // Returns all employees in map
    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employees.values());
    }

    // Returns a single employee who has a matching UUID
    public Employee getEmployeeByUuid(UUID uuid) {
        Employee employee = employees.get(uuid);

        if (employee == null) {
            throw new EmployeeNotFoundException("Employee with uuid " + uuid + " not found");
        }

        return employee;
    }

    // Validates input and creates a new employee
    public Employee createEmployee(IndividualEmployeeRequest request) {
        if (request == null) {
            throw new InvalidEmployeeException("Employee request cannot be null");
        }

        if (request.getFirstName() == null || request.getFirstName().isBlank()) {
            throw new InvalidEmployeeException("First name cannot be null or empty");
        }

        if (request.getLastName() == null || request.getLastName().isBlank()) {
            throw new InvalidEmployeeException("Last name cannot be null or empty");
        }

        if (request.getSalary() == null || request.getSalary() < 0) {
            throw new InvalidEmployeeException("Salary cannot be null or below 0");
        }

        if (request.getAge() == null || request.getAge() < 0) {
            throw new InvalidEmployeeException("Age cannot be null or below 0");
        }

        if (request.getJobTitle() == null || request.getJobTitle().isBlank()) {
            throw new InvalidEmployeeException("Job title cannot be null or empty");
        }

        if (request.getEmail() == null || request.getEmail().isBlank()) {
            throw new InvalidEmployeeException("Email cannot be null or empty");
        }

        if (request.getContractHireDate() == null) {
            throw new InvalidEmployeeException("Contract hire date cannot be null");
        }

        IndividualEmployee employee = new IndividualEmployee();
        employee.setUuid(UUID.randomUUID());
        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setFullName(request.getFirstName() + " " + request.getLastName());
        employee.setSalary(request.getSalary());
        employee.setAge(request.getAge());
        employee.setJobTitle(request.getJobTitle());
        employee.setEmail(request.getEmail());
        employee.setContractHireDate(request.getContractHireDate());

        employees.put(employee.getUuid(), employee);
        return employee;
    }
}
