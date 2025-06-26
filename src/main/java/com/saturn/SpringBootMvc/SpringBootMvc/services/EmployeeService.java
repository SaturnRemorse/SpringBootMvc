package com.saturn.SpringBootMvc.SpringBootMvc.services;

import ch.qos.logback.core.model.Model;
import com.saturn.SpringBootMvc.SpringBootMvc.dtos.EmployeeDTO;
import com.saturn.SpringBootMvc.SpringBootMvc.entities.EmployeeEntity;
import com.saturn.SpringBootMvc.SpringBootMvc.repositories.EmployeeRepo;
import lombok.RequiredArgsConstructor;
import org.apache.el.util.ReflectionUtil;
import org.modelmapper.ModelMapper;
import org.springframework.util.ReflectionUtils;
import org.springframework.stereotype.Service;


import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepo employeeRepo;
    private final ModelMapper mapper;

    public EmployeeService(EmployeeRepo employeeRepo, ModelMapper mapper){
        this.employeeRepo = employeeRepo;
        this.mapper = mapper;
    }

    public Optional<EmployeeDTO> getEmployeeById(Long id){
        Optional<EmployeeEntity> employee = employeeRepo.findById(id);
        return employee.map(employee1 -> mapper.map(employee1, EmployeeDTO.class) );

    }

    public List<EmployeeDTO> getAllEmployees(){
        List<EmployeeEntity> employees= employeeRepo.findAll();
        return employees.stream().map(employee -> mapper.map(employee, EmployeeDTO.class)).toList();
    }


    public EmployeeDTO saveEmployee(EmployeeDTO employee) {
        EmployeeEntity emp = mapper.map(employee, EmployeeEntity.class);
        return mapper.map(employeeRepo.save(emp), EmployeeDTO.class);
    }

    public EmployeeDTO updateEmployeeById(Long empId, EmployeeDTO employee) {
        EmployeeEntity emp = mapper.map(employee, EmployeeEntity.class);
        emp.setId(empId);
        EmployeeEntity updatedEmployee = employeeRepo.save(emp);
        return mapper.map(updatedEmployee, EmployeeDTO.class);
    }

    public boolean isExistsById(Long empId){
        return employeeRepo.existsById(empId);
    }

    public boolean deleteEmpById(Long empId) {
        boolean deleted = false;
        if(isExistsById(empId)){
            employeeRepo.deleteById(empId);
            deleted = true;
        }
        return deleted;
    }

    public EmployeeDTO updatePartialEmployee(Long empId, Map<String, Object> data) {
        if(!isExistsById(empId)){
            return null;
        }
        EmployeeEntity entity = employeeRepo.findById(empId).get();
        data.forEach((key, value) ->{
            Field fieldToBeUpdated = ReflectionUtils.findField(EmployeeEntity.class, key );
            fieldToBeUpdated.setAccessible(true);
            ReflectionUtils.setField(fieldToBeUpdated, entity, value);

        });
        return mapper.map(employeeRepo.save(entity), EmployeeDTO.class);

    }
}
