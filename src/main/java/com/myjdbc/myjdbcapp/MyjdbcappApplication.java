package com.myjdbc.myjdbcapp;

import com.myjdbc.myjdbcapp.dao.UserDao;
import com.myjdbc.myjdbcapp.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;

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
//		logger.info("Num of rows affected - " + userDao.addUser(2, "Ankit Gautam", "gautamankit99@gmail.com"));
//		logger.info("Num of rows affected - " + userDao.addUser(3, "Ankit Sahoo", "sahoo.ankit85@gmail.com"));
//		logger.info("Num of rows affected - " + userDao.addUser(4, "Sheik Sahil", "sahilsheik99@gmail.com"));
//		logger.info("Num of rows affected - " + userDao.addUser(5, "Vikramaditya Swain", "swainvikramaditya89@gmail.com"));
//		logger.info("All users - {}", userDao.getAllUsers());
//		logger.info("User by id 1 - {}", userDao.getUserById(1));
//		logger.info("updating user mail -> {}", userDao.updateUserEmailById(1, "amit-kumar.sahu@capgemini1.com"));
//		logger.info("username by email -> {}", userDao.findUsernameByEmail("sahoo.ankit85@gmail.com"));
//		logger.info("Deleting user with id {} is success {}", 3, userDao.deleteUserWithId(3));\
//		List<User> users = Arrays.asList(
//				new User(1, "Amit", "amitsahu@gmail.com"),
//				new User(2, "Ankit", "ankit@gmail.com"),
//				new User(3, "Sahil", "sahil@gmail.com"),
//				new User(4, "Shubham", "shubham@yahoomail.com"),
//				new User(5, "Swarna", "swarna@gmail.com"),
//				new User(6, "Vikram", "vikram@gmail.com"),
//				new User(7, "Abhishek", "abhishek@gmail.com"),
//				new User(8, "Sampu", "sampu@gmail.com"),
//				new User(9, "Suru", "suru@gmail.com"),
//				new User(10, "Anant", "anant@gmail.com"),
//				new User(11, "Aniket", "aniket@gmail.com"),
//				new User(12, "Shivani", "shivani@gmail.com"),
//				new User(13, "Priyanka", "priyankanahak@gmail.com"));
//		logger.info("Doing bulk update and adding list of users - > {}", userDao.addBulkUsers(users));
//		String domain = "gmail.com";
//		logger.info("Users with domain - {} are  -> {}",domain, userDao.getUsersByEmailDomain(domain));
//		String mail = "shivani@gmail.com";
//		logger.info("User with email - {} exists - {}", mail, userDao.emailExists(mail));
//		logger.info("Users deleted - {}", userDao.deleteAllUsers());
//		logger.info("paged users data - {}", userDao.getUsersPaged(10, 5));
//		String email  = "priyankanahak@gmail.com";
//		logger.info("Get user by email - {} - {}", email, userDao.getUserByEmail(email));
		User user = new User(20, "UniqueUser", "UniqueUser@gmail.com");
		logger.info("Adding user if not exists already! - {} - status - {}", user.getEmail(), userDao.addUserIfNotExists(user));
	}
}
