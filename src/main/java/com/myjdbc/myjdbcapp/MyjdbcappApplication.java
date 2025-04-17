package com.myjdbc.myjdbcapp;

import com.myjdbc.myjdbcapp.dao.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyjdbcappApplication implements CommandLineRunner {

	@Autowired
	UserDao userDao;

	Logger logger = LoggerFactory.getLogger(this.getClass());

	public static void main(String[] args) {
		SpringApplication.run(MyjdbcappApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		logger.info("Num of rows affected - " + userDao.addUser(1, "Amit Sahu", "sahu.amit85@gmail.com"));
		logger.info("All users - {}", userDao.getAllUsers());
		logger.info("User by id 3 - {}", userDao.getUserById(3));
	}
}
