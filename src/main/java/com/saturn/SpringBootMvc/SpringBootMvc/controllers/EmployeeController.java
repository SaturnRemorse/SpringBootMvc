package com.saturn.SpringBootMvc.SpringBootMvc.controllers;


import com.saturn.SpringBootMvc.SpringBootMvc.dtos.EmployeeDTO;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "/emp")
public class EmployeeController {

    @GetMapping("/{empId}")
    public EmployeeDTO getEmployeeById(@PathVariable Long empId){
        return new EmployeeDTO(empId,"Minnie","minnie@gidle.com",28, LocalDate.of(2017,2,24), true);
    }

    @GetMapping("/")
    public String getAllEmployees(@RequestParam(required = false) String age){
        return age;

    }

    @PostMapping()
    public EmployeeDTO createNewEmployee(@RequestBody EmployeeDTO employee){
        return employee;
    }

    @PutMapping()
    public Long updateEmployeeById(@PathVariable Long empId){
        return empId;
    }

}
