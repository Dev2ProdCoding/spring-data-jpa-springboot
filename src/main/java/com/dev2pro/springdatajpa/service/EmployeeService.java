package com.dev2pro.springdatajpa.service;

import com.dev2pro.springdatajpa.entity.Employee;
import com.dev2pro.springdatajpa.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public List<Employee> saveEmployeeData(List<Employee> employeeList) {
        return employeeRepository.saveAll(employeeList);
    }

    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long empId) {
        return employeeRepository.findByEmpId(empId);
    }

    public List<Employee> deleteEmployeeByDesignation(String designation) {
        return employeeRepository.deleteByDesignation(designation);
    }

    public Employee getEmployeeByDesignation(String designation) {
        return employeeRepository.findTopByDesignationOrderBySalaryDesc(designation);
    }

    public Page<Employee> getEmployeePagination(Integer pageNumber, Integer pageSize, String sortProperty) {
        Pageable pageable = null;
        if(null!=sortProperty){
            pageable = PageRequest.of(pageNumber, pageSize, Sort.Direction.ASC,sortProperty);
        }else {
            pageable = PageRequest.of(pageNumber, pageSize, Sort.Direction.ASC,"name");
        }
        return employeeRepository.findAll(pageable);
    }
}
