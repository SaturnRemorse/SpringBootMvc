package com.saturn.SpringBootMvc.SpringBootMvc.repositories;

import com.saturn.SpringBootMvc.SpringBootMvc.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepo extends JpaRepository<EmployeeEntity, Long> {
}
