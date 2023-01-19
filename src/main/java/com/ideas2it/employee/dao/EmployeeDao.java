package com.ideas2it.employee.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ideas2it.employee.model.Employee;

@Repository
public interface EmployeeDao extends JpaRepository<Employee, Integer> {
	@Query("FROM Employee e WHERE e.firstName LIKE %:name%")
	List<Employee> findByName(@Param("name")String name);
}
