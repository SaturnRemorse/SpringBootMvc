package com.saturn.SpringBootMvc.SpringBootMvc.services;

import ch.qos.logback.core.model.Model;
import com.saturn.SpringBootMvc.SpringBootMvc.dtos.EmployeeDTO;
import com.saturn.SpringBootMvc.SpringBootMvc.entities.EmployeeEntity;
import com.saturn.SpringBootMvc.SpringBootMvc.repositories.EmployeeRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepo employeeRepo;
    private final ModelMapper mapper;

    public EmployeeService(EmployeeRepo employeeRepo, ModelMapper mapper){
        this.employeeRepo = employeeRepo;
        this.mapper = mapper;
    }

    public EmployeeDTO getEmployeeById(Long id){
        EmployeeEntity employee = employeeRepo.findById(id).orElse(null);
        return mapper.map(employee, EmployeeDTO.class);

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

    public boolean deleteEmpById(Long empId) {
        boolean deleted = false;
        if(employeeRepo.existsById(empId)){
            employeeRepo.deleteById(empId);
            deleted = true;
        }
        return deleted;
    }
}
