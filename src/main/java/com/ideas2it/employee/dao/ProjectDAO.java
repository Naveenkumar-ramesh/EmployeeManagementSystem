package com.ideas2it.employee.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ideas2it.employee.model.Project;

@Repository
public interface ProjectDAO extends JpaRepository<Project, Integer> {
	@Query("FROM Project p WHERE p.projectName LIKE %:name%")
	List<Project> findByName(@Param("name")String name);
}
