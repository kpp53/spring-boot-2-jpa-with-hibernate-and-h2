package com.test.sqlinjection.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{

	
	@Query("SELECT id FROM Student WHERE name= :name and passportnumber = :passportNumber")
	public List<Long> findStudentByName(@Param("name") String name,@Param("passportNumber") String passportnumber);
	
	
}
