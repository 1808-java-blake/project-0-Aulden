package com.revature.screens;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.beans.CurrentUser;
import com.revature.beans.User;
import com.revature.daos.UserDao;

public class LoginScreen implements Screen {
	private Scanner scan = new Scanner(System.in);
	private UserDao ud = UserDao.currentUserDao;
	private Logger log = Logger.getRootLogger();

	@Override
	public Screen start() {
		System.out.println("Enter Username or type Register to sign up: ");
		String username = scan.nextLine();
		if ("register".equalsIgnoreCase(username)) {
			log.info("User entered Register Screen");
			return new RegisterUserScreen();
		}
		
		System.out.println("Enter Password: ");
		String password = scan.nextLine();

		User currentUser = ud.findByUsernameAndPassword(username, password);
		
		if (currentUser != null) {
			CurrentUser.username = currentUser.getUsername();
			CurrentUser.password = currentUser.getPassword();
			return new HomeScreen();
		}

		log.info("User entered incorrect credentials");
		System.out.println("Unable to login");
		return this;
	}

}
