package com.example.restweb.springrest.services;

import com.example.restweb.springrest.dto.EmployeeDTO;
import com.example.restweb.springrest.entity.EmployeeEntity;
import com.example.restweb.springrest.repositories.EmployeeRepository;
import org.apache.el.util.ReflectionUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
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

        return employeeEntity!=null?modelMapper.map(employeeEntity,EmployeeDTO.class):null;
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

    public EmployeeDTO updateEmployeeById(Long employeeId, Map<String,Object> updates) {
        EmployeeEntity employee = employeeRepository.findById(employeeId).get();

        updates.forEach((field,value)->{
            Field fieldToBeUpdated = ReflectionUtils.findField(EmployeeEntity.class,field);
            fieldToBeUpdated.setAccessible(true);
            ReflectionUtils.setField(fieldToBeUpdated,employee,value);
        });

        return modelMapper.map(employeeRepository.save(employee),EmployeeDTO.class);

    }

    public Boolean deleteEmployee(Long employeeId) {
        EmployeeEntity employee = employeeRepository.findById(employeeId).orElse(null);
        if(employee==null)
            return false;
        employeeRepository.deleteById(employeeId);
        return true;
    }
}
