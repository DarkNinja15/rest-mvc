package com.example.restweb.springrest.controller;

import com.example.restweb.springrest.dto.EmployeeDTO;
import com.example.restweb.springrest.services.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path="/employee/{employeeId}")
    public EmployeeDTO getEmployeeById(@PathVariable Long employeeId){
        return employeeService.findById(employeeId);
    }

    @PostMapping(path = "/employee")
    public EmployeeDTO createEmployee(@RequestBody EmployeeDTO employeeDTO){
        return employeeService.createEmployee(employeeDTO);
    }

    @GetMapping(path = "/employees")
    public List<EmployeeDTO> getAllEmployees(){
        return employeeService.getAllEmployees();
    }
}
