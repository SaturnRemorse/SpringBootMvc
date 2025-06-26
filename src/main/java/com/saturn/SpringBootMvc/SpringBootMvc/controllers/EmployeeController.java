package com.saturn.SpringBootMvc.SpringBootMvc.controllers;


import com.saturn.SpringBootMvc.SpringBootMvc.dtos.EmployeeDTO;
import com.saturn.SpringBootMvc.SpringBootMvc.services.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/emp")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path="/")
    public List<EmployeeDTO> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    @GetMapping(path = "/{empId}")
    public EmployeeDTO getEmployeeById(@PathVariable Long empId){
        return employeeService.getEmployeeById(empId);
    }

    @PostMapping(path = "/")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employee){
        return employeeService.saveEmployee(employee);
    }

    @PutMapping(path = "/{empId}")
    public EmployeeDTO updateEmployeeById(@PathVariable Long empId,@RequestBody EmployeeDTO employee){
        return employeeService.updateEmployeeById(empId, employee);
    }

    @DeleteMapping(path = "/{empId}")
    public boolean deleteEmployeeById(@PathVariable Long empId){
        return employeeService.deleteEmpById(empId);
    }
}
