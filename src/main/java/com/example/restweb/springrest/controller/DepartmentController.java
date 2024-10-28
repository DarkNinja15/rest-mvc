package com.example.restweb.springrest.controller;

import com.example.restweb.springrest.advices.ApiResponse;
import com.example.restweb.springrest.dto.DepartmentDTO;
import com.example.restweb.springrest.exceptions.ResourceNotFoundException;
import com.example.restweb.springrest.services.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping(path = "/departments")
    public ResponseEntity<ApiResponse<?>> createDepartment(@RequestBody @Valid DepartmentDTO departmentDTO){
        DepartmentDTO savedDepartmentDTO = departmentService.createDepartment(departmentDTO);
        return new ResponseEntity<>(new ApiResponse<>(savedDepartmentDTO), HttpStatus.CREATED);
    }

    @GetMapping(path = "/departments/{departmentId}")
    public ResponseEntity<ApiResponse<?>> getDepartmentById(@PathVariable Long departmentId){
        DepartmentDTO departmentDTO = departmentService.getDepartmentById(departmentId);
        if(departmentDTO==null){
            throw new ResourceNotFoundException("Department not found!");
        }
        return new ResponseEntity<>(new ApiResponse<>(departmentDTO),HttpStatus.OK);
    }

    @GetMapping(path = "/departments")
    public ResponseEntity<ApiResponse<?>> getAllDepartments(){
        List<DepartmentDTO> departments = departmentService.getAllDepartments();
        return new ResponseEntity<>(new ApiResponse<>(departments),HttpStatus.OK);
    }

    @PatchMapping(path = "/departments/{departmentId}")
    public ResponseEntity<ApiResponse<?>> updateDepartmentById(@PathVariable Long departmentId,@RequestBody Map<String,Object> updates){
        DepartmentDTO updatedDepartmentDTO = departmentService.updateDepartmentById(departmentId,updates);
        if(updatedDepartmentDTO==null){
            throw new ResourceNotFoundException("Department not found!");
        }
        return new ResponseEntity<>(new ApiResponse<>(updatedDepartmentDTO),HttpStatus.OK);
    }

    @DeleteMapping(path = "/departments/{departmentId}")
    public ResponseEntity<ApiResponse<?>> deleteDepartmentById(@PathVariable Long departmentId){
        boolean isDeleted = departmentService.deleteDepartmentById(departmentId);
        return new ResponseEntity<>(new ApiResponse<>(isDeleted),HttpStatus.OK);
    }

}
