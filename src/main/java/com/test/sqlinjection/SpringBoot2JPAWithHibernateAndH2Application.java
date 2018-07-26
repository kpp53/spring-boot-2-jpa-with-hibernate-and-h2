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

		
		String studentName="'Ranga'";
		String studentpassportNumber="'E1234567' OR 1=1    ";
		
		String id= "'1' or 1=1 ";
		
		Query createQuery = entityManager.createNativeQuery("select passportnumber from Student where name ="+ studentName +" and id ="+ id );
		
		
		List<String> resultList2 = createQuery.getResultList();
		
		System.out.println(resultList2);
		
		List<Long> findStudentByName = repository.findStudentByName(studentName,studentpassportNumber);
		
		
		
		System.out.println(findStudentByName);
		
		
		
		Query query = entityManager.createNativeQuery("SELECT count(*) FROM student where name = ?1");
		List<String> resultList = query.setParameter(1, "Ravi").getResultList();
		
		System.out.println("Result ::"+resultList);
		
		
		

		repository.deleteById(10002L);

		logger.info("All users -> {}", repository.findAll());
	}
}
