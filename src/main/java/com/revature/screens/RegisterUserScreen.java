package com.revature.screens;

import java.util.Scanner;

import com.revature.beans.Account;
import com.revature.beans.User;
import com.revature.daos.AccountDao;
import com.revature.daos.UserDao;

public class RegisterUserScreen implements Screen {
	private Scanner scan = new Scanner(System.in);
	private UserDao ud = UserDao.currentUserDao;
	private AccountDao ad = AccountDao.currentAccountDao;

	@Override
	public Screen start() {
		User u = new User();
		System.out.println("Enter new username");
		u.setUsername(scan.nextLine());
		System.out.println("Enter password");
		u.setPassword(scan.nextLine());
		System.out.println("Enter first name");
		u.setFirstName(scan.nextLine());
		System.out.println("Enter last name");
		u.setLastName(scan.nextLine());
		System.out.println("Enter age");
		String age = scan.nextLine();
		System.out.println("Are you an admin? (y/n)");
		String adm = scan.nextLine();
		
		if("y".equalsIgnoreCase(adm)) {
			u.setAdmin(true);
		}
		else {
			u.setAdmin(false);
		}
		
		//create account
		Account a = new Account();
		ad.createAccount(a);
		u.addAccountId(a.getId());
		
		try {
			u.setAge(Integer.valueOf(age));
			ud.createUser(u);
			
		} catch (NumberFormatException e) {
			System.out.println("Invalid number");
		}
		
		return new LoginScreen();
	}

}
