package com.example.employee.repository;

import com.example.employee.model.Employee;

import java.util.List; 

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	@Query("SELECT e FROM Employee e WHERE e.active = :active AND e.approve = :approve")
	List<Employee> getActiveApprovedEmployees(@Param("active") String active, @Param("approve") int approve);
} 