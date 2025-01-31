package com.example.restweb.springrest.controller;

import com.example.restweb.springrest.dto.EmployeeDTO;
import com.example.restweb.springrest.exceptions.ResourceNotFoundException;
import com.example.restweb.springrest.services.EmployeeService;
import jakarta.validation.Valid;
import org.modelmapper.internal.bytebuddy.implementation.bytecode.Throw;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path="/employee/{employeeId}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long employeeId){
        EmployeeDTO employeeDTO = employeeService.findById(employeeId);
        if(employeeDTO==null){
            throw new ResourceNotFoundException("Employee Not Found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(employeeDTO);
    }

    @PostMapping(path = "/employee")
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody @Valid EmployeeDTO employeeDTO){
        EmployeeDTO savedEmployee = employeeService.createEmployee(employeeDTO);
        return ResponseEntity.status(HttpStatus.OK).body(savedEmployee);
    }

    @GetMapping(path = "/employees")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees(){
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.getAllEmployees());
    }

    @PatchMapping(path = "/employee/{employeeId}")
    public ResponseEntity<EmployeeDTO> updateEmployeeById(@RequestBody Map<String,Object> updates, @PathVariable Long employeeId){
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.updateEmployeeById(employeeId,updates));
    }

    @DeleteMapping(path = "/employee/{employeeId}")
    public ResponseEntity<Boolean> deleteEmployee(@PathVariable Long employeeId){
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.deleteEmployee(employeeId));
    }
}
