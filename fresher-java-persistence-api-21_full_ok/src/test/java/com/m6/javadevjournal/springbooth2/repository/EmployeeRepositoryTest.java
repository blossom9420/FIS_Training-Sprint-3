package com.m6.javadevjournal.springbooth2.repository;

import com.m6.javadevjournal.springbooth2.model.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class EmployeeRepositoryTest {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void getAllEmployees() {
        List employees = new ArrayList();
        employeeRepository.findAll().forEach(employee -> employees.add(employee));
        System.out.println(employees);
    }

    @Test
    public void findById(){
        Employee emp = employeeRepository.findById(1).get();
        assertEquals("Amy", emp.getName());
    }
}