package com.test.sqlinjection;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.test.sqlinjection.repository.StudentRepository;

@SpringBootApplication
public class SpringBoot2JPAWithHibernateAndH2Application implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	StudentRepository repository;
	
	@Autowired
	EntityManager entityManager;
	
	

	public static void main(String[] args) {
		SpringApplication.run(SpringBoot2JPAWithHibernateAndH2Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		logger.info("Student id 10001 -> {}", repository.findById(10001L));

		
		String name="";
		
		List<Long> findStudentByName = repository.findStudentByName(name);
		
		System.out.println(findStudentByName);
		
		
		
		Query query = entityManager.createNativeQuery("SELECT count(*) FROM student where name = ?1");
		List<String> resultList = query.setParameter(1, "Ravi").getResultList();
		
		System.out.println("Result ::"+resultList);
		
		
		

		repository.deleteById(10002L);

		logger.info("All users -> {}", repository.findAll());
	}
}