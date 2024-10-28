package com.example.restweb.springrest.services;

import com.example.restweb.springrest.dto.DepartmentDTO;
import com.example.restweb.springrest.entity.DepartmentEntity;
import com.example.restweb.springrest.repositories.DepartmentRepository;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;


    public DepartmentService(DepartmentRepository departmentRepository, ModelMapper modelMapper) {
        this.departmentRepository = departmentRepository;
        this.modelMapper = modelMapper;
    }


    public DepartmentDTO createDepartment(@Valid DepartmentDTO departmentDTO) {
        DepartmentEntity toSaveEntity = modelMapper.map(departmentDTO,DepartmentEntity.class);
        departmentRepository.save(toSaveEntity);
        return departmentDTO;
    }

    public DepartmentDTO getDepartmentById(Long departmentId) {
        DepartmentEntity departmentEntity = departmentRepository.findById(departmentId).orElse(null);
        if(departmentEntity==null){
            return null;
        }
        return modelMapper.map(departmentEntity,DepartmentDTO.class);
    }

    public List<DepartmentDTO> getAllDepartments() {
        return departmentRepository
                .findAll()
                .stream()
                .map(departmentEntity -> modelMapper.map(departmentEntity,DepartmentDTO.class))
                .collect(Collectors.toList());
    }

    public DepartmentDTO updateDepartmentById(Long departmentId, Map<String, Object> updates) {
        DepartmentEntity departmentEntity = departmentRepository.findById(departmentId).orElse(null);
        if(departmentEntity==null){
            return null;
        }

        updates.forEach((field,value)->{
            Field fieldToBeUpdated = ReflectionUtils.findField(DepartmentEntity.class,field);
            fieldToBeUpdated.setAccessible(true);
            ReflectionUtils.setField(fieldToBeUpdated,departmentEntity,value);
        });

        return modelMapper.map(departmentRepository.save(departmentEntity),DepartmentDTO.class);

    }

    public boolean deleteDepartmentById(Long departmentId) {
        departmentRepository.deleteById(departmentId);
        return true;
    }
}
