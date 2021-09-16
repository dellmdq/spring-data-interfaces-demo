package com.luv2code.springboot.cruddemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.webmvc.RepositoryRestController;

import com.luv2code.springboot.cruddemo.entity.Employee;

@RepositoryRestResource(path="members")//changing endpoint path
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	//CRUD methods for free
}
