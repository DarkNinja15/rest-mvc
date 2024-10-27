package com.example.restweb.springrest.services;

import com.example.restweb.springrest.dto.EmployeeDTO;
import com.example.restweb.springrest.entity.EmployeeEntity;
import com.example.restweb.springrest.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

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
        if(employeeEntity==null){
            return new EmployeeDTO(employeeId,"Anime","email",12, LocalDate.of(2024,3,2),false);
        }
        return modelMapper.map(employeeEntity,EmployeeDTO.class);
    }
}
