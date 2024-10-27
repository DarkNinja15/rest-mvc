package com.example.restweb.springrest.controller;

import com.example.restweb.springrest.dto.EmployeeDTO;
import com.example.restweb.springrest.services.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @PatchMapping(path = "/employee/{employeeId}")
    public EmployeeDTO updateEmployeeById(@RequestBody Map<String,Object> updates, @PathVariable Long employeeId){
        return employeeService.updateEmployeeById(employeeId,updates);
    }

    @DeleteMapping(path = "/employee/{employeeId}")
    public Boolean deleteEmployee(@PathVariable Long employeeId){
        return employeeService.deleteEmployee(employeeId);
    }
}
