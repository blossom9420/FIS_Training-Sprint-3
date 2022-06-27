package com.m6.javadevjournal.springbooth2.service;

import com.m6.javadevjournal.springbooth2.model.Employee;

import com.m6.javadevjournal.springbooth2.model.Model;
import com.m6.javadevjournal.springbooth2.repository.EmployeeRepository;
import com.m6.javadevjournal.springbooth2.repository.LocationRepository;
import com.m6.javadevjournal.springbooth2.repository.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.xml.stream.Location;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public List getAllEmployees() {
        List employees = new ArrayList();
        employeeRepository.findAll().forEach(employee -> employees.add(employee));
        return employees;
    }

    public Employee getEmployeeById(int id) {
        return employeeRepository.findById(id).get();
    }

    public void saveOrUpdate(Employee employee) {
        employeeRepository.save(employee);
    }

    public void delete(int id) {
        employeeRepository.deleteById(id);
    }

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    ModelRepository modelRepository;


    // để đảm bảo cả 2 cùng hoàn thành thì đặt transactional lên trên
    @Transactional
    public void createLocationAndModel(Location location, Model model){
        locationRepository.create(location);
        modelRepository.create(model);
    }
}