package com.revature.screens;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.beans.Account;
import com.revature.beans.User;
import com.revature.daos.AccountDao;
import com.revature.daos.UserDao;

public class RegisterUserScreen implements Screen {
	private Scanner scan = new Scanner(System.in);
	private UserDao ud = UserDao.currentUserDao;
	private AccountDao ad = AccountDao.currentAccountDao;
	private Logger log = Logger.getRootLogger();

	@Override
	public Screen start() {
		User u = new User();
		System.out.println("Enter new username");
		u.setUsername(scan.nextLine());
		log.trace("Username entered");
		System.out.println("Enter password");
		u.setPassword(scan.nextLine());
		log.trace("Password entered");
		System.out.println("Enter first name");
		u.setFirstName(scan.nextLine());
		log.trace("First name entered");
		System.out.println("Enter last name");
		u.setLastName(scan.nextLine());
		log.trace("Last name entered");
		System.out.println("Enter age");
		String age = scan.nextLine();
		log.trace("Age entered");
		System.out.println("Are you an admin? (y/n)");
		String adm = scan.nextLine();
		log.trace("Admin status entered");
		
		if("y".equalsIgnoreCase(adm)) {
			u.setAdmin(true);
		}
		else {
			u.setAdmin(false);
		}
		
		if(ud.findByUsername(u.getUsername()) != null) {
			log.debug("User " + u.getUsername() + " logged in successfully");
			log.info(u.getUsername() + "entered Login screen");
			return new LoginScreen();
		}
		
		//create account
		Account a = new Account();
		ad.createAccount(a);
		u.addAccountId(a.getId());
		log.debug("Account " + a.getId() + " created successfully");
		
		try {
			u.setAge(Integer.valueOf(age));
			ud.createUser(u);
			log.debug("User " + u.getUsername() + " created successfully");
			
		} catch (NumberFormatException e) {
			log.info("Invalid number entered");
			System.out.println("Invalid number");
		}
		
		log.info("User entered Login screen");
		return new LoginScreen();
	}

}
