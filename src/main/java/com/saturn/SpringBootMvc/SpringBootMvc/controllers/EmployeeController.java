package com.saturn.SpringBootMvc.SpringBootMvc.controllers;


import com.saturn.SpringBootMvc.SpringBootMvc.annotations.EmployeeAgeValidation;
import com.saturn.SpringBootMvc.SpringBootMvc.dtos.EmployeeDTO;
import com.saturn.SpringBootMvc.SpringBootMvc.exceptions.ResourceNotFoundException;
import com.saturn.SpringBootMvc.SpringBootMvc.services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping(path = "/emp")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path="/")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees(){
        return new ResponseEntity<>(employeeService.getAllEmployees(), HttpStatus.OK);
    }

    @GetMapping(path = "/{empId}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long empId){
        Optional<EmployeeDTO> employeeDTO = employeeService.getEmployeeById(empId);
        return employeeDTO.map(employeeDTO1 -> ResponseEntity.ok(employeeDTO1)).orElseThrow(() -> new ResourceNotFoundException("Employee Not Found with id "+empId));
    }



    @PostMapping(path = "/")
    public ResponseEntity<EmployeeDTO> saveEmployee(@RequestBody @Valid EmployeeDTO employee){
        return new ResponseEntity<>(employeeService.saveEmployee(employee),HttpStatus.CREATED);
    }

    @PutMapping(path = "/{empId}")
    public ResponseEntity<EmployeeDTO> updateEmployeeById(@PathVariable Long empId,@RequestBody @Valid EmployeeDTO employee){
        return new ResponseEntity<>(employeeService.updateEmployeeById(empId, employee), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{empId}")
    public ResponseEntity<Boolean> deleteEmployeeById(@PathVariable Long empId){
        if(employeeService.deleteEmpById(empId)){
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.notFound().build();

    }

    @PatchMapping(path = "/{empId}")
    public ResponseEntity<EmployeeDTO> updatePartialEmpById(@PathVariable Long empId, @RequestBody Map<String, Object> data){
        EmployeeDTO employeeDTO = employeeService.updatePartialEmployee(empId, data);
        if(employeeDTO == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(employeeDTO);
    }
}
