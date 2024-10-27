package com.example.restweb.springrest.services;

import com.example.restweb.springrest.dto.EmployeeDTO;
import com.example.restweb.springrest.entity.EmployeeEntity;
import com.example.restweb.springrest.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    public EmployeeDTO findById(Long employeeId) {
        EmployeeEntity employeeEntity = employeeRepository.findById(employeeId).orElse(null);

        return modelMapper.map(employeeEntity,EmployeeDTO.class);
    }

    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        EmployeeEntity toSaveEntity = modelMapper.map(employeeDTO,EmployeeEntity.class);
        employeeRepository.save(toSaveEntity);
        return employeeDTO;
    }

    public List<EmployeeDTO> getAllEmployees() {
        List<EmployeeEntity> employees=employeeRepository.findAll();
        return employees.stream().map(employeeEntity -> {
            return modelMapper.map(employeeEntity,EmployeeDTO.class);
        }).collect(Collectors.toList());
    }
}
