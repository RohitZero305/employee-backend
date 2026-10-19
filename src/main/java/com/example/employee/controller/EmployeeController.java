package com.example.employee.controller;

import com.example.employee.model.Employee;
import com.example.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin(origins = "*") // Angular sathi CORS allow kela
public class EmployeeController {

    @Autowired 
    private EmployeeRepository employeeRepository;

    @GetMapping
    public List<Employee> getAllEmployees() {	
        return employeeRepository.getActiveApprovedEmployees("A", 1); 
    }

    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    } 

    @PutMapping("/{id}") 
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails) {
        Employee employee = employeeRepository.findById(id).orElseThrow();
        employee.setName(employeeDetails.getName());
        employee.setEmail(employeeDetails.getEmail());
        employee.setDepartment(employeeDetails.getDepartment());
        employee.setPhoneno(employeeDetails.getPhoneno());
        return employeeRepository.save(employee);  
    }

   
    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow();
        employee.setActive("D"); // Status 'D' (Deactive) kela
        employeeRepository.save(employee); // Database madhe save kela
    }
}